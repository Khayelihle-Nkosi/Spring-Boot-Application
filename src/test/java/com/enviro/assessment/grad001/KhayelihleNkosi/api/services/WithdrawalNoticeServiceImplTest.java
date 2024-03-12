package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.eProductType.ProductType;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.Product;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Personal;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.investorRepository.InvestorRepository;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.productRepository.ProductRepository;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.withdrawalNoticeRepository.WithdrawalNoticeRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WithdrawalNoticeServiceImplTest {
    @Mock
    private WithdrawalNoticeRepository noticeRepository;
    @Mock
    private InvestorRepository investorRepository;
    @Mock
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<WithdrawalNotice> noticeArgumentCaptor;
    @InjectMocks
    private WithdrawalNoticeServiceImpl noticeService;

    @Test
    void shouldCalculateAge() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-10");
        int actualAge = noticeService.calculateAge(date);
        assertThat(25).isEqualTo(actualAge);
    }

    @Test
    public void shouldPassSuccessfulWithdrawal() throws Exception {
        double withdrawalAmount = 100.0;
        long productId = 1L;

        Investor investor = new Investor();
        investor.setPersonal(new Personal());
        investor.getPersonal().setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1950-01-01"));

        Product product = new Product();
        product.setCurrentBalance(1000.00);
        product.setType(ProductType.SAVINGS);
        product.setName("Saving Account");
        product.setInvestor(investor);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(investorRepository.findById(product.getInvestor().getId())).thenReturn(Optional.of(investor));

        WithdrawalNotice notice = noticeService.createWithdrawalNotice(productId, withdrawalAmount);
        verify(noticeRepository, Mockito.times(1)).save(noticeArgumentCaptor.capture());

        assertThat(noticeArgumentCaptor).isNotNull();
        assertThat(noticeArgumentCaptor.getValue()).isEqualTo(notice);
    }

    @Test()
    public void shouldFailWithdrawalExceedsBalance() {
        Long productId = 1L;
        double withdrawalAmount = 3000.0;

        Investor investor = new Investor();

        Product product = new Product();
        product.setCurrentBalance(1000.00);
        product.setInvestor(investor);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(investorRepository.findById(product.getInvestor().getId())).thenReturn(Optional.of(investor));

        assertThatThrownBy(() -> noticeService.createWithdrawalNotice(productId, withdrawalAmount))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Withdrawal amount cannot exceed current balance");
    }

    @Test()
    public void shouldFailWithdrawalExceeds90Percent() {
        Long productId = 1L;
        double withdrawalAmount = 2900.0;

        Investor investor = new Investor();

        Product product = new Product();
        product.setCurrentBalance(3000.0);
        product.setInvestor(investor);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(investorRepository.findById(product.getInvestor().getId())).thenReturn(Optional.of(investor));

        assertThatThrownBy(() -> noticeService.createWithdrawalNotice(productId, withdrawalAmount))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Withdrawal amount cannot exceed 90% of current balance");
    }

    @Test()
    public void shouldFailWithdrawalForRetirementProductUnderAge() throws Exception {
        Long productId = 1L;
        double withdrawalAmount = 100.0;
        Investor investor = new Investor();
        investor.setPersonal(new Personal());
        investor.getPersonal().setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-01"));

        Product product = new Product();
        product.setCurrentBalance(1000.00);
        product.setType(ProductType.RETIREMENT);
        product.setInvestor(investor);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(investorRepository.findById(product.getInvestor().getId())).thenReturn(Optional.of(investor));

        assertThatThrownBy(() -> noticeService.createWithdrawalNotice(productId, withdrawalAmount))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Investor must be over the age of 65 for retirement withdrawal");
    }
}