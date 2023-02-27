package com.nttd.billeteradig.service;

import java.util.List;

import com.nttd.billeteradig.dto.ValidationCardDto;
import com.nttd.billeteradig.dto.ValidationDebitDto;
import com.nttd.billeteradig.entity.PhoneEntity;

import io.smallrye.mutiny.Uni;

public interface PhoneService {

    public Uni<List<PhoneEntity>> getAllPhone();

    public Uni<PhoneEntity> addPhone(PhoneEntity phoneEntity);

    public Uni<PhoneEntity> update(String id, PhoneEntity phoneEntity);

    public Uni<PhoneEntity> findPhoneByTelephone(String telephone);

    public Uni<PhoneEntity> delete(String id);

    public Uni<ValidationCardDto> getValidationCard(String cardNumber);

    public Uni<ValidationDebitDto> validationDebit(long IdBANKCARD);

}
