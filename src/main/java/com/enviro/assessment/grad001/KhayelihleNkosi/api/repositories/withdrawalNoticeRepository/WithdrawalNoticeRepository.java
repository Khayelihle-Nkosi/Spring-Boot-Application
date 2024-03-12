package com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.withdrawalNoticeRepository;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {
    List<WithdrawalNotice> findAllByProductIdAndTimestampBetween(Long productId, Date fromDate, Date toDate);
}
