package com.nttd.billeteradig.service;

import com.nttd.billeteradig.entity.PhoneEntity;

import io.smallrye.mutiny.Uni;

public interface PhoneService {

    public Uni<String> getAllPhone();

    public Uni<PhoneEntity> addPhone(PhoneEntity phoneEntity);

    public Uni<PhoneEntity> update(String id, PhoneEntity phoneEntity);

    public Uni<PhoneEntity> findPhoneByIdCustomer(long idcustomer);

}
