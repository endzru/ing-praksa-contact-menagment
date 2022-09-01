package com.example.contactmenagment.services;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SMSVerificationService {
    private final UserService userService;

    private final Environment environment;
    private final String  TWILIO_ACCOUNT_SID ="ACd3aba34f3f4c44c923fc8536f3282cb1";
    private final String TWILIO_AUTH_TOKEN="34234c4f9e4c9b72fa01c8e8e244b8fc";
    private final String TWILIO_SERVICE_SID="VA61af6c0aace5eb70b1e12a615b0d04a2";

    @Autowired
    public SMSVerificationService(UserService userService, Environment environment) {
        this.environment = environment;
        this.userService = userService;
    }
    @Transactional
    public ResponseEntity<String> sendVerificationMessage() {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        if(userService.getLoggedInUser().getStatus().equals("NOT_VERIFIED")){
            Verification.creator(TWILIO_SERVICE_SID, userService.getLoggedInUser().getPhonenumber(),
                    "sms").create();
            return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Message not sent, Account already verified", HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional
    public ResponseEntity<String> verifyUser(String verificationCode) {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        VerificationCheck verificationCheck =
                VerificationCheck.creator(TWILIO_SERVICE_SID)
                        .setTo(userService.getLoggedInUser().getPhonenumber())
                        .setCode(verificationCode).create();
        if(verificationCheck.getValid()){
            userService.validateUser();
            return new ResponseEntity<String>(verificationCheck.getStatus(), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(verificationCheck.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

}
