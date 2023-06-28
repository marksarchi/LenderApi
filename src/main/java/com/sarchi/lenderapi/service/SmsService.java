package com.sarchi.lenderapi.service;

import com.sarchi.lenderapi.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {
    @Autowired
    TwilioConfig twilioConfig;

    public void sendSms(String msisdn,String message) {
        try{
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            PhoneNumber to = new PhoneNumber(msisdn);
            MessageCreator creator = Message.creator(to, from, message);

            creator.create();

            log.info("<<<<<<< Message sent successfully >>>>>>");
        }catch(Exception e){
            log.info("<<<<<<<An error occurred , failed to send sms>>>>>>");
        }
    }
}
