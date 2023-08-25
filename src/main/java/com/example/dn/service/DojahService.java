package com.example.dn.service;

import com.example.dn.config.DojahProperties;
import com.example.dn.gateway.SmsGateway;
import com.example.dn.model.SmsRequest;
import com.example.dn.model.SmsResponse;
import com.example.dn.model.Status;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DojahService implements SmsGateway {

    private final DojahProperties properties;
    private final JsonService jsonService;
    private final OkHttpClient client;

    public DojahService(DojahProperties properties, JsonService jsonService) {
        this.properties = properties;
        this.jsonService = jsonService;
        this.client = new OkHttpClient.Builder()
                .build();
    }

    @Override
    public SmsResponse sendSms(SmsRequest sms) {
        String url = properties.getUrl() + "/api/v1/dojah/send/sms";
        try (Response response = client.newCall(create(url, sms)).execute()) {
            String json = response.body().string();
            log.info("Raw Json: [{}]", json);
            if (response.isSuccessful()) {
                return jsonService.fromJson(json, SmsResponse.class);
            }
            return SmsResponse.of(Status.FAILURE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Request create(String url, SmsRequest sms) {
        String json = jsonService.toJson(sms);
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();
    }

}
