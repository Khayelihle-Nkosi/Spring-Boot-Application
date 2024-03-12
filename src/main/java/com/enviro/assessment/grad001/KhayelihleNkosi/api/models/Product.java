package com.enviro.assessment.grad001.KhayelihleNkosi.api.models;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.eProductType.ProductType;
import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor.Investor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@SuppressWarnings({"JpaDataSourceORMInspection", "UnusedReturnValue"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private Double currentBalance;
    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference()
    private Investor investor;

    public Product() {
    }

    public Long getId() {
        return id;
    }
    public ProductType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public Double getCurrentBalance() {
        return currentBalance;
    }
    public Investor getInvestor() {
        return investor;
    }

    public Product setType(ProductType type) {
        this.type = type;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
        return this;
    }

    public Product setInvestor(Investor investor) {
        this.investor = investor;
        return this;
    }
}
