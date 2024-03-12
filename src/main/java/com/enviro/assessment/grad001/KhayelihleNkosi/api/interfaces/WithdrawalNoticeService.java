package com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;

public interface WithdrawalNoticeService {
    WithdrawalNotice createWithdrawalNotice(Long productId, double amount) throws Exception;
}
