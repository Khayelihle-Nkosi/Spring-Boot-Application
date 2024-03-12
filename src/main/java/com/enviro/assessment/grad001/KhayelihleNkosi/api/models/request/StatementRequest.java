package com.enviro.assessment.grad001.KhayelihleNkosi.api.models.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class StatementRequest {
    public Long productId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    public Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    public Date endDate;
}
