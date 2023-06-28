package com.sarchi.lenderapi.service;

import com.sarchi.lenderapi.config.Constants;
import com.sarchi.lenderapi.domain.Loan;
import com.sarchi.lenderapi.domain.LoanRepaymentStatus;
import com.sarchi.lenderapi.domain.LoanRequest;
import com.sarchi.lenderapi.exceptions.BadRequestException;
import com.sarchi.lenderapi.exceptions.NotFoundException;
import com.sarchi.lenderapi.repository.LoanRepository;
import com.sarchi.lenderapi.repository.UserRepository;
import com.sarchi.lenderapi.domain.RepayRequest;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@EnableScheduling
@Slf4j
public class LoanService {

    UserRepository userRepository;
    LoanRepository loanRepository;
    SmsService smsService;


    public ResponseEntity requestLoan(LoanRequest request) throws Exception {

        var userExists = userRepository.findByMsisdn(request.getMsisdn());
        if (userExists.isEmpty()) {
            throw new NotFoundException("User with provided msisdn not found");
        }

        //Create loan
        Loan loan = new Loan();
        loan.setLoanAmount(request.getAmount());
        loan.setCurrency("KES");
        loan.setUserID(userExists.get().getId());
        loan.setCreatedDate(LocalDateTime.now());
        loan.setUpdatedDate(LocalDateTime.now());
        loan.setRepaymentStatus(LoanRepaymentStatus.UN_PAID);
        loan.setRePaidAmount(0.0);
        Loan savedLoan = loanRepository.save(loan);
        if (savedLoan == null) {
            throw new Exception("Loan creation failed, please contact admin");
        }
        var requestLoanSuccessMessage = "Dear " + userExists.get().getUserName() + " a loan of  " + loan.getLoanAmount()
                + " has been credited to your account";

        notifyUserBySms(userExists.get().getMsisdn(), requestLoanSuccessMessage);
        return ResponseEntity.ok(loan);
    }

    private void notifyUserBySms(String msisdn, String message) {
        try {
            smsService.sendSms(msisdn, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity repayLoan(RepayRequest request) throws BadRequestException {
        var userExists = userRepository.findByMsisdn(request.getMsisdn());
        if (userExists.isEmpty()) {
            throw new BadRequestException("Provided user does not exist");
        }
        var loanExist = loanRepository.findByIdAndUserId(request.getLoanId(), userExists.get().getId());
        if (loanExist.isEmpty()) {
            throw new BadRequestException("No loan found for provided user");

        }
        if(loanExist.get().getRepaymentStatus().equals(LoanRepaymentStatus.FULLY_PAID)){
            throw new BadRequestException("Loan is fully paid");
        }

        var paidAmount = loanExist.get().getRePaidAmount();
        paidAmount += request.getPaymentAmount();
        loanExist.get().setRePaidAmount(paidAmount);
        var loanStatus = paidAmount >= loanExist.get().getLoanAmount()? LoanRepaymentStatus.FULLY_PAID:LoanRepaymentStatus.PARTIALLY_PAID;
        loanExist.get().setRepaymentStatus(loanStatus);
        var updatedLoan = loanRepository.save(loanExist.get());

        var loanPaymentSuccessMessage = "Dear " + userExists.get().getUserName() + ", you have made a payment of " + request.getPaymentAmount()
                + " your loan status is "+ loanStatus;

        notifyUserBySms(userExists.get().getMsisdn(), loanPaymentSuccessMessage);
        return ResponseEntity.ok(updatedLoan);
    }
    @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight every day
    public void updateUnpaidLoans() {
        LocalDateTime defaultPeriod = LocalDateTime.now().minusMonths(Constants.LOAN_DEFAULT_PERIOD);
        List<Loan> unpaidLoans = loanRepository.findAllByCreatedDateBeforeAndRepaymentStatusIn(defaultPeriod,
                List.of(LoanRepaymentStatus.UN_PAID,LoanRepaymentStatus.PARTIALLY_PAID));

        for (Loan loan : unpaidLoans) {
            // Update the loan status or perform any other actions
            loan.setRepaymentStatus(LoanRepaymentStatus.DEFAULTED);
            loan.setUpdatedDate(LocalDateTime.now());
        }

        // Save the updated loans
        loanRepository.saveAll(unpaidLoans);
    }



}

