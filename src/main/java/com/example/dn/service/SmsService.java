package com.example.dn.service;

import com.example.dn.config.KafkaConfig;
import com.example.dn.model.SmsRequest;
import com.example.dn.model.SmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SmsService {

    private final KafkaConfig kafkaConfig;
    private final SmsGatewayProvider gatewayProvider;
    private final KafkaTemplate<String, SmsRequest> kafkaTemplate;

    public SmsService(KafkaConfig kafkaConfig, SmsGatewayProvider gatewayProvider,
                      KafkaTemplate<String, SmsRequest> kafkaTemplate) {
        this.kafkaConfig = kafkaConfig;
        this.gatewayProvider = gatewayProvider;
        this.kafkaTemplate = kafkaTemplate;
    }

    public SmsResponse sendSms(SmsRequest smsRequest) {
        var response = gatewayProvider.getProvider().sendSms(smsRequest);
        log.info("Service: [{}] -> [{}]", smsRequest, response);
        return response;
    }

    public void senSmsAsync(SmsRequest request) {
        kafkaTemplate.send(kafkaConfig.getSmsTopic(), UUID.randomUUID().toString(), request);
    }
}
