package com.example.contactmenagment.controllers;


import com.example.contactmenagment.services.SMSVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TwilioController {
    private final SMSVerificationService smsVerificationService;

    @Autowired
    public TwilioController(SMSVerificationService smsVerificationService) {
        this.smsVerificationService = smsVerificationService;
    }


    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        return smsVerificationService.sendVerificationMessage();
    }

    @GetMapping("/verify/{verificationCode}")
    public ResponseEntity<String> verify(@PathVariable String verificationCode){
        return smsVerificationService.verifyUser(verificationCode);
    }



}
