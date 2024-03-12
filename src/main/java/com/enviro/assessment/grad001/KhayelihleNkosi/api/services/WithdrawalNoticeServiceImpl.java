package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.eProductType.ProductType;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.WithdrawalNoticeService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.Product;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.investorRepository.InvestorRepository;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.productRepository.ProductRepository;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.withdrawalNoticeRepository.WithdrawalNoticeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class WithdrawalNoticeServiceImpl implements WithdrawalNoticeService {
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final ProductRepository productRepository;
    private final InvestorRepository investorRepository;

    public WithdrawalNoticeServiceImpl(WithdrawalNoticeRepository withdrawalNoticeRepository, ProductRepository productRepository, InvestorRepository investorRepository) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.productRepository = productRepository;
        this.investorRepository = investorRepository;
    }

    @Override
    @Transactional
    public WithdrawalNotice createWithdrawalNotice(Long productId, double withdrawalAmount) throws ValidationException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ValidationException("Product with Id " + productId + " not found"));
        Investor investor = investorRepository.findById(product.getInvestor().getId()).orElseThrow(() -> new ValidationException("Investor with Id " + product.getInvestor().getId() + " not found"));

        if (withdrawalAmount > product.getCurrentBalance())
            throw new ValidationException("Withdrawal amount cannot exceed current balance");

        if (withdrawalAmount > (product.getCurrentBalance() * 0.9))
            throw new ValidationException("Withdrawal amount cannot exceed 90% of current balance");

        if (product.getType() == ProductType.RETIREMENT && calculateAge(investor.getPersonal().getDob()) <= 65)
            throw new ValidationException("Investor must be over the age of 65 for retirement withdrawal");

        WithdrawalNotice notice = new WithdrawalNotice();
        notice.setProduct(product);
        notice.setWithdrawalAmount(withdrawalAmount);
        notice.setTimestamp(new Date());
        saveWithdrawalNotice(notice);
        return notice;
    }

    private void saveWithdrawalNotice(WithdrawalNotice notice) {
        withdrawalNoticeRepository.save(notice);
    }

    protected int calculateAge(Date dateOfBirth) {
        LocalDate dob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }
}
