package com.nttd.billeteradig.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountObject {

    private long IdAccountCustomer;
    private String flag_creation;
    private double current_amount;
    private double starting_amount;
    private String flag_account;
    private String startdate;
    private String courtdate;
    private String state;
    private long IdCustomer;
    private long IdBANKCARD;

}
