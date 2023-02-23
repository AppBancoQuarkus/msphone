package com.nttd.billeteradig.service.impl;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.billeteradig.service.IncrementService;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PhoneServiceImpl  implements PhoneService {

    @Inject
    IncrementService  incrementService;

    //@RestClient
    //UserApi userApi;


    @ConfigProperty(name="mensaje.general")
    String mensajeGeneral;
   
    @Override
    public Uni<String> getAllPhone(){
        return Uni.createFrom().item(mensajeGeneral);
    }

}
