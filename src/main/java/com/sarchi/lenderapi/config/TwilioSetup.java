package com.sarchi.lenderapi.config;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TwilioSetup {

    private final TwilioConfig twilioConfiguration;

    @Autowired
    public TwilioSetup(TwilioConfig twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getToken());
    }
}
