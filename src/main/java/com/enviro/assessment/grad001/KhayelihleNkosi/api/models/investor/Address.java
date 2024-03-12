package com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor;

import jakarta.persistence.*;
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity()
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "houseNumber")
    private Integer houseNumber;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "province")
    private String province;
    @Column(name = "postal")
    private Integer postalCode;
    @Column(name = "country")
    private String country;
    public Address() {
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
