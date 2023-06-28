package com.sarchi.lenderapi.repository;

import com.sarchi.lenderapi.domain.Loan;
import com.sarchi.lenderapi.domain.LoanRepaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findByIdAndUserId(Long loanId, Long userId);

    List<Loan> findAllByCreatedDateBeforeAndRepaymentStatusIn(LocalDateTime sixMonthsAgo, List<LoanRepaymentStatus> statuses);
}
