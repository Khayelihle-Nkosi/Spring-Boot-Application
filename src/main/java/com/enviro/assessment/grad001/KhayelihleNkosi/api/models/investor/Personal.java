package com.enviro.assessment.grad001.KhayelihleNkosi.api.models.investor;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused", "UnusedReturnValue"})
@Entity
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idNumber")
    private Long idNumber;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    public Personal() {
    }
    public Date getDob() {
        return dob;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Personal setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public Personal setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Personal setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Personal setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Personal setDob(Date dob) {
        this.dob = dob;
        return this;
    }
}
