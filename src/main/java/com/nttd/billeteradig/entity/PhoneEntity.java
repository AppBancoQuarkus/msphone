package com.nttd.billeteradig.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import org.bson.codecs.pojo.annotations.BsonProperty;

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

    @BsonProperty("code_customer")
    private long idPhone;
    private String name;
    private String lastname;
    private String telephone;
    private String email;
    private Integer password;
    private long idAccount;

}
