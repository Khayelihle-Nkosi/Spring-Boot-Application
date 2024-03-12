package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.investorRepository.InvestorRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InvestorServiceTest {
    @Mock
    private InvestorRepository investorRepository;
    @InjectMocks
    private InvestorServiceImpl investorService;

    @Test
    @DisplayName("This should pass and return the investor info when a valid id is passed")
    void shouldFindInvestorInfoById() {
        long investorId = 1L;

        Investor investor = new Investor();
        Mockito.when(investorRepository.findById(investorId)).thenReturn(Optional.of(investor));

        var actualInvestor = investorService.getInvestorInfo(investorId);
        assertThat(actualInvestor).isNotNull();
    }

    @Test
    @DisplayName("This should fail and throw an IllegalArgumentException when id is not greater than 0")
    void shouldFailWhenInvestorIdIsZero() {
        assertThatThrownBy(() -> investorService.getInvestorInfo(-1L))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Investor Id is required and needs to be greater than zero");
    }

    @Test
    @DisplayName("This should fail and throw an IllegalArgumentException when investor id is not found")
    void shouldFailWhenInvestorIdDoesNotExists() {
        long investorId = 10L;

        assertThatThrownBy(() -> investorService.getInvestorInfo(investorId))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Investor with id " + investorId + " not found");
    }
}