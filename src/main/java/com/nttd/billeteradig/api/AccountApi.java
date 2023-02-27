package com.nttd.billeteradig.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttd.billeteradig.api.response.AccountResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient
@Path("/account")
public interface AccountApi {


    @GET
    @Path("/validation/{IdBANKCARD}")
    public Uni<AccountResponse> validationDebit(long IdBANKCARD);
    
}
