package com.nttd.billeteradig.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class ValidationCardDto {

    private boolean respuesta;
    private long idcard;
    private int pin;
    private String duedate;

    public ValidationCardDto(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public ValidationCardDto(boolean respuesta, long idcard, int pin, String duedate) {
        this.respuesta = respuesta;
        this.idcard = idcard;
        this.pin = pin;
        this.duedate = duedate;
    }

}
