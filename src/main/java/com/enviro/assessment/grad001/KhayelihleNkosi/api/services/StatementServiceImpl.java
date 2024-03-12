package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.StatementService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.withdrawalNoticeRepository.WithdrawalNoticeRepository;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.Date;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final CsvFileGenerator csvFileGenerator;

    public StatementServiceImpl(WithdrawalNoticeRepository withdrawalNoticeRepository, CsvFileGenerator csvFileGenerator) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.csvFileGenerator = csvFileGenerator;
    }

    @Override
    public void downloadStatement(Long productId, Date startDate, Date endDate, Writer writer) {
        List<WithdrawalNotice> notices = withdrawalNoticeRepository.findAllByProductIdAndTimestampBetween(productId, startDate, endDate);
        csvFileGenerator.writeNoticesToCsv(notices, writer);
    }
}
