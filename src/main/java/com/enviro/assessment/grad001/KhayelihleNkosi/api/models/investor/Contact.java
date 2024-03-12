package com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor;

import jakarta.persistence.*;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    public Contact() {
    }

    public String getEmail() {
        return email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }
}
