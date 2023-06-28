package com.sarchi.lenderapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "fintech_loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column( nullable = false)
    private Double loanAmount;

    @Column( nullable = false)
    private Double rePaidAmount;

    @Column( nullable = false)
    private String currency;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Column(name = "user_idv",nullable = false)
    private Long userID;

    @Column( nullable = false)
    private LoanRepaymentStatus repaymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_idv", referencedColumnName = "id", updatable = false ,insertable = false)
    private User user;
}
