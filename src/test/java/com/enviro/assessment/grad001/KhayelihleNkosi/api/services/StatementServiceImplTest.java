package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.withdrawalNoticeRepository.WithdrawalNoticeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {
    @Mock
    private WithdrawalNoticeRepository noticeRepository;
    @Mock
    private CsvFileGenerator csvFileGenerator;
    @Mock
    private Writer writer;
    @InjectMocks
    private StatementServiceImpl statementService;
    @Captor
    private ArgumentCaptor<List<WithdrawalNotice>> noticesCaptor;

    @Test
    public void testDownloadStatement() throws Exception {
        Long productId = 1L;
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24));
        List<WithdrawalNotice> notices = new ArrayList<>();

        when(noticeRepository.findAllByProductIdAndTimestampBetween(productId, startDate, endDate))
                .thenReturn(notices);

        statementService.downloadStatement(productId, startDate, endDate, writer); // Assuming writer is mocked/ignored

        verify(noticeRepository).findAllByProductIdAndTimestampBetween(productId, startDate, endDate);
        verify(csvFileGenerator).writeNoticesToCsv(noticesCaptor.capture(), any(Writer.class));

        List<WithdrawalNotice> capturedNotices = noticesCaptor.getValue();
        assertThat(capturedNotices).isEmpty();
    }
}