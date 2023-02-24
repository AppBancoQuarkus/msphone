package com.nttd.billeteradig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.billeteradig.entity.PhoneEntity;
import com.nttd.billeteradig.service.IncrementService;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PhoneServiceImpl implements PhoneService {

    @Inject
    IncrementService incrementService;

    @ConfigProperty(name = "valor.activo")
    String valorActivo;

    @ConfigProperty(name = "valor.inactivo")
    String valorInactivo;

    @ConfigProperty(name = "exception.general")
    String exceptionGeneral;

    // @RestClient
    // UserApi userApi;

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

}
