package com.example.dn.controller;

import com.example.dn.model.SmsRequest;
import com.example.dn.model.SmsResponse;
import com.example.dn.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sms")
@Slf4j
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SmsResponse> sendSms(@RequestBody SmsRequest request) {
        log.info("Sending message [{}]", request);
        SmsResponse response = smsService.sendSms(request);
        return ResponseEntity.ok(response);
    }
}
