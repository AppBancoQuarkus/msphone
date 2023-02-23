package com.nttd.billeteradig.service;

import io.smallrye.mutiny.Uni;

public interface PhoneService {

    public Uni<String> getAllPhone();

}
