package com.nttd.billeteradig.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
    private int code;
    private String message;
    private String errorMessage;
    private String description;
    private AccountObject accountEntity;
}
