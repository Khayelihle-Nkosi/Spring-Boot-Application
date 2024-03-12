package com.enviro.assessment.grad001.KhayelihleNkosi.api.services;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void writeNoticesToCsv(List<WithdrawalNotice> notices, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("Withdrawal Notice ID", "Product ID", "Withdrawal Amount", "Date");
            for (WithdrawalNotice notice : notices) {
                printer.printRecord(notice.getId(), notice.getProduct().getId(), notice.getWithdrawalAmount(), notice.getTimestamp());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
