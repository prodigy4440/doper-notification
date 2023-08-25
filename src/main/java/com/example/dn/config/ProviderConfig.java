package com.example.dn.config;

import com.example.dn.gateway.Provider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ProviderConfig {

    @Value("${provider}")
    private Provider provider;
}
