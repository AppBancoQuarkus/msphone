package com.nttd.billeteradig.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttd.billeteradig.api.request.BankCardRequest;
import com.nttd.billeteradig.api.response.BankCardResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient
@Path("/bankcard")
public interface BankCardApi {

    @GET
    public Uni<BankCardResponse> getAllBankCard(BankCardRequest bankCardRequest);
}
