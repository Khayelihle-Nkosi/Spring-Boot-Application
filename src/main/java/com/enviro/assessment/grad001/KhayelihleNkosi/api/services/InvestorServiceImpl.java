package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.InvestorService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.investorRepository.InvestorRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InvestorServiceImpl implements InvestorService {
    private final InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public Investor getInvestorInfo(Long investorId) throws ValidationException {
        if (investorId <= 0L) {
            throw new ValidationException("Investor Id is required and needs to be greater than zero");
        }

        Optional<Investor> investor = investorRepository.findById(investorId);

        if (investor.isEmpty()) throw new ValidationException("Investor with id " + investorId + " not found");

        return investor.get();
    }

}
