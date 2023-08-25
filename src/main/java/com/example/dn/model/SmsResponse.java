package com.example.dn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SmsResponse {
    private String message;
    private Status status;

    public static SmsResponse of(Status status) {
        return new SmsResponse("Failed", status);
    }
}
