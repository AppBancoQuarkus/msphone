package com.nttd.billeteradig.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MongoEntity(collection = "DWMPHONE")
public class PhoneEntity extends ReactivePanacheMongoEntity {

    private String name;
    private String lastname;
    private String telephone;
    private String email;
    private String password;
    private long idAccount;
    private String state;

}
