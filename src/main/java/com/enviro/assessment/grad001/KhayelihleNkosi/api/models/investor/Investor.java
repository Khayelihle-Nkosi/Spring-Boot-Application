package com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused", "UnusedReturnValue"})
@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_id")
    private Personal personal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference()
    private List<Product> products;

    public Investor() {
    }

    public Long getId() {
        return id;
    }
    public Personal getPersonal() {
        return personal;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Investor setPersonal(Personal personal) {
        this.personal = personal;
        return this;
    }

    public Investor setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Investor setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Investor setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
