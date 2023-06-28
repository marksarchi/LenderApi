package com.sarchi.lenderapi.domain;

import lombok.Data;

@Data
public class LoanRequest {
    String msisdn;
    Double amount;
}
