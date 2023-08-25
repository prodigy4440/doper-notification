package com.example.dn.gateway;

import com.example.dn.model.SmsRequest;
import com.example.dn.model.SmsResponse;

public interface SmsGateway {
    public SmsResponse sendSms(SmsRequest sms);
}
