package com.nttd.billeteradig.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    private long IdAccountCustomer;
    private boolean respuesta;
    private String flag_creation;
    private double current_amount;
    private long IdBANKCARD;
    private String state;
    
}
