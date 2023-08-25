package com.example.dn.service;

import com.example.dn.model.SmsRequest;
import com.example.dn.model.SmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {

    private final SmsGatewayProvider gatewayProvider;

    public SmsService(SmsGatewayProvider gatewayProvider) {
        this.gatewayProvider = gatewayProvider;
    }

    public SmsResponse sendSms(SmsRequest smsRequest) {
        var response = gatewayProvider.getProvider().sendSms(smsRequest);
        log.info("Service: [{}] -> [{}]", smsRequest, response);
        return response;
    }
}
