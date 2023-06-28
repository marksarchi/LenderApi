package com.sarchi.lenderapi.repository;

import com.sarchi.lenderapi.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findByIdAndUserId(Long loanId, Long userId);
}
