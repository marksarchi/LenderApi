package com.sarchi.lenderapi.domain;

import lombok.Data;

@Data
public class RepayRequest {
    String msisdn;
    Long loanId;
    Double paymentAmount;
}
