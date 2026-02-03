package com.example.SecurityPractiseWithJWT.entity.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Token {
    @NonNull
    private String access_token;
}
