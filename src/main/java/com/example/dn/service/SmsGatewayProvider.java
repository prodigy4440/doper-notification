package com.example.dn.service;

import com.example.dn.config.ProviderConfig;
import com.example.dn.gateway.SmsGateway;
import org.springframework.stereotype.Service;

@Service
public class SmsGatewayProvider {

    private final ProviderConfig providerConfig;
    private final DojahService dojahService;
    private final HollatagService hollatagService;

    public SmsGatewayProvider(ProviderConfig providerConfig,
                              DojahService dojahService,
                              HollatagService hollatagService) {
        this.providerConfig = providerConfig;
        this.dojahService = dojahService;
        this.hollatagService = hollatagService;
    }

    public SmsGateway getProvider() {
        return switch (providerConfig.getProvider()) {
            case DOJAH -> dojahService;
            case HOLLATAG -> hollatagService;
            default -> throw new RuntimeException("No provider selected");
        };
    }
}
