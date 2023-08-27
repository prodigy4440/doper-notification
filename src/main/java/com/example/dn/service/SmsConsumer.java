package com.example.dn.service;

import com.example.dn.model.SmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsConsumer {

    private final SmsGatewayProvider gatewayProvider;

    public SmsConsumer(SmsGatewayProvider gatewayProvider) {
        this.gatewayProvider = gatewayProvider;
    }

    @KafkaListener(id = "dope-notification", topics = "${broker.sms-topic}")
    public void receiverSmsRequest(SmsRequest smsRequest) {
        log.info("Message: [{}]", smsRequest);
        var response = gatewayProvider.getProvider().sendSms(smsRequest);
        log.info("Service: [{}] -> [{}]", smsRequest, response);
    }
}
