package com.example.contactmenagment.controllers;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TwilioController {

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        String sid = "ACd3aba34f3f4c44c923fc8536f3282cb1";
        String authToken = "34234c4f9e4c9b72fa01c8e8e244b8fc";
        Twilio.init(sid, authToken);

        Message.creator(new PhoneNumber("+381691134201"),
                new PhoneNumber("+18145594777"), "Hello from Twilio 📞").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
