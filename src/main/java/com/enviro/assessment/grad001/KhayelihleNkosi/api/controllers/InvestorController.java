package com.enviro.assessment.grad001.KhayelihleNkosi.api.controllers;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.InvestorService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.StatementService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.interfaces.WithdrawalNoticeService;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.WithdrawalNotice;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.request.StatementRequest;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.request.WithdrawalNoticeRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping("/api/v1/investor")
public class InvestorController {
    private final InvestorService investorServiceInterface;
    private final WithdrawalNoticeService withdrawalNoticeServiceInterface;
    private final StatementService statementServiceInterface;

    public InvestorController(InvestorService investorServiceInterface, WithdrawalNoticeService withdrawalNoticeServiceInterface, StatementService statementServiceInterface) {
        this.investorServiceInterface = investorServiceInterface;
        this.withdrawalNoticeServiceInterface = withdrawalNoticeServiceInterface;
        this.statementServiceInterface = statementServiceInterface;
    }

    @GetMapping("/{id}")
    public Investor getInvestorInfo(@PathVariable Long id) throws Exception {
        return investorServiceInterface.getInvestorInfo(id);
    }

    @PostMapping("/withdrawal")
    public WithdrawalNotice createWithdrawal(@RequestBody WithdrawalNoticeRequest noticeRequest) throws Exception {
        return withdrawalNoticeServiceInterface.createWithdrawalNotice(noticeRequest.productId, noticeRequest.withdrawalAmount);
    }

    @GetMapping("/generate-csv")
    public ResponseEntity<?> generateCsvFile(@RequestBody() StatementRequest request,
                                             HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Statement.csv");
        PrintWriter writer = response.getWriter();

        statementServiceInterface.downloadStatement(request.productId, request.startDate, request.endDate, writer);
        writer.close();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
