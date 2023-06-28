package com.sarchi.lenderapi.resource;

import com.sarchi.lenderapi.domain.LoanRequest;
import com.sarchi.lenderapi.domain.RepayRequest;
import com.sarchi.lenderapi.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@AllArgsConstructor
public class LoanController {
    LoanService loanService;
    @PostMapping("request")
    public ResponseEntity requestLoan(@RequestBody LoanRequest request) throws Exception {
        return  loanService.requestLoan(request);
    }
    @PostMapping("repay")
    public ResponseEntity repayLoan(@RequestBody RepayRequest request) throws Exception {
        return  loanService.repayLoan(request);
    }
}
