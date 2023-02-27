package com.nttd.billeteradig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.nttd.billeteradig.api.BankCardApi;
import com.nttd.billeteradig.api.request.BankCardRequest;
import com.nttd.billeteradig.api.response.AccountApi;
import com.nttd.billeteradig.dto.ValidationCardDto;
import com.nttd.billeteradig.dto.ValidationDebitDto;
import com.nttd.billeteradig.entity.PhoneEntity;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PhoneServiceImpl implements PhoneService {

   

    @ConfigProperty(name = "valor.activo")
    String valorActivo;

    @ConfigProperty(name = "valor.inactivo")
    String valorInactivo;

    @ConfigProperty(name = "exception.general")
    String exceptionGeneral;

    @ConfigProperty(name = "valor.code.exitoso")
    int code_ok;

    @RestClient
    BankCardApi bankCardApi;

    @RestClient
    AccountApi accountApi;

    @ConfigProperty(name = "mensaje.general")
    String mensajeGeneral;

    @Override
    public Uni<List<PhoneEntity>> getAllPhone() {
        return PhoneEntity.findAll().list();
    }

    @Override
    public Uni<PhoneEntity> addPhone(PhoneEntity phoneEntity) {
        if (phoneEntity.getPassword().length() != 6) {
            throw new NotFoundException(exceptionGeneral);
        }
        phoneEntity.setState(valorActivo);
        return phoneEntity.persist();
    }

    @Override
    public Uni<PhoneEntity> update(String id, PhoneEntity phoneEntity) {
        Uni<PhoneEntity> postuni = PhoneEntity.findById(new ObjectId(id));
        return postuni.flatMap(post -> {
            if (phoneEntity.getPassword().length() != 6) {
                throw new NotFoundException(exceptionGeneral);
            }
            post.setPassword(phoneEntity.getPassword());
            post.setEmail(phoneEntity.getEmail());
            return post.persistOrUpdate();
        });
    }

    @Override
    public Uni<PhoneEntity> findPhoneByTelephone(String telephone) {
        Map<String, Object> params = new HashMap<>();
        params.put("state", valorActivo);
        params.put("telephone", telephone);

        return PhoneEntity.find("state=:state and telephone=:telephone", params).firstResult();
    }

    @Override
    public Uni<PhoneEntity> delete(String id) {
        Uni<PhoneEntity> postdelete = PhoneEntity.findById(id);
        return postdelete.flatMap(delete -> {
            delete.setState(valorInactivo);
            return delete.persistOrUpdate();
        });
    }

    // METODO PARA BUSCAR NUMERO DE TARJETA
    public Uni<ValidationCardDto> getValidationCard(String cardNumber) {
        BankCardRequest bcrq = new BankCardRequest(cardNumber);
        return bankCardApi.getAllBankCard(bcrq).onItem().transform(resp -> {
            ValidationCardDto validation = new ValidationCardDto(false);
            if (resp != null && resp.getCode() == code_ok) {
                validation.setRespuesta(true);
                validation.setIdcard(resp.getBankCardEntity().getIdBANKCARD());
                validation.setPin(resp.getBankCardEntity().getPin());
                validation.setDuedate(resp.getBankCardEntity().getDuedate());
            }
            return validation;
        });
    }

    // METODO QUE VALIDA SI ES DEBITO
    public Uni<ValidationDebitDto> validationDebit(long IdBANKCARD) {
        return accountApi.validationDebit(IdBANKCARD).onItem().transform(resp -> {
            ValidationDebitDto validation = new ValidationDebitDto(false);
            if (resp != null && resp.getCode() == code_ok) {
                validation.setRespuesta(true);
                validation.setIdAccountCustomer(resp.getAccountEntity().getIdAccountCustomer());
                validation.setCurrent_amount(resp.getAccountEntity().getCurrent_amount());
                validation.setFlag_creation(resp.getAccountEntity().getFlag_creation());
                validation.setIdBANKCARD(resp.getAccountEntity().getIdBANKCARD());
                validation.setState(resp.getAccountEntity().getState());
            }
            return validation;
        });
    }

}
