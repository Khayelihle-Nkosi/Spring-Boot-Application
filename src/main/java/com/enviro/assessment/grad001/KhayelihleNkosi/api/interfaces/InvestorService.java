package com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
public interface InvestorService {
    Investor getInvestorInfo(Long investorId) throws Exception;
}
