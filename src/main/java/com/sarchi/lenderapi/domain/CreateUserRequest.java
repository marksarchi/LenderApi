package com.sarchi.lenderapi.domain;

import lombok.Data;

@Data
public class CreateUserRequest {
    String msisdn;
    String firstName;
    String lastName;
    String countryCode;
}
