package com.sarchi.lenderapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "fintech_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private String firstName;

    private String lastName;

    private String msisdn;
    private String countryCode;
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private  List<Loan> loans;

    public String getUserName() {
        return this.firstName.concat(" ").concat(this.lastName);
    }

}
