package com.enviro.assessment.grad001.KhayelihleNkosi.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings({"JpaDataSourceORMInspection", "UnusedReturnValue"})
@Entity
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double WithdrawalAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public WithdrawalNotice() {
    }

    public Long getId() {
        return id;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date getTimestamp() {
        return timestamp;
    }

    public Double getWithdrawalAmount() {
        return WithdrawalAmount;
    }

    public Product getProduct() {
        return product;
    }

    public WithdrawalNotice setWithdrawalAmount(Double withdrawalAmount) {
        WithdrawalAmount = withdrawalAmount;
        return this;
    }

    public WithdrawalNotice setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public WithdrawalNotice setProduct(Product product) {
        this.product = product;
        return this;
    }
}
