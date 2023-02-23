package com.nttd.billeteradig.service.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.billeteradig.entity.PhoneEntity;
import com.nttd.billeteradig.service.IncrementService;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PhoneServiceImpl implements PhoneService {

    @Inject
    IncrementService incrementService;

    // @RestClient
    // UserApi userApi;

    @ConfigProperty(name = "mensaje.general")
    String mensajeGeneral;

    @Override
    public Uni<String> getAllPhone() {
        return Uni.createFrom().item(mensajeGeneral);
    }

    @Override
    public Uni<PhoneEntity> addPhone(PhoneEntity phoneEntity) {

        return Uni.createFrom().item(new PhoneEntity());
    }

    @Override
    public Uni<PhoneEntity> update(String id, PhoneEntity phoneEntity) {
        Uni<PhoneEntity> postuni = PhoneEntity.findById(new ObjectId(id));
        return postuni
                .onItem().transform(post -> {
                    post.setPassword(phoneEntity.getPassword());
                    post.setEmail(phoneEntity.getEmail());
                    post.setTelephone(phoneEntity.getTelephone());
                    return post;
                });
    }

    @Override
    public Uni<PhoneEntity> findPhoneByIdCustomer(long idPhone) {
        // Uni<PhoneEntity> phone = PhoneEntity.f;
        return null;
    }

}
