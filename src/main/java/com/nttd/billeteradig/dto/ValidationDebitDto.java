package com.nttd.billeteradig.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationDebitDto {

    private long IdAccountCustomer;
    private boolean respuesta;
    private String flag_creation;
    private double current_amount;
    private long IdBANKCARD;
    private String state;

    public ValidationDebitDto(boolean respuesta) {
        this.respuesta = respuesta;
    }
}
