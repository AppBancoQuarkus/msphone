package com.nttd.billeteradig.api.response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient
@Path("/account")
public interface AccountApi {

    @GET
    @Path("/IdBANKCARD/{IdBANKCARD}")
    public Uni<AccountResponse> findAccountByIdCard(long IdBANKCARD);

    @GET
    @Path("/validation/{IdBANKCARD}")
    public Uni<AccountResponse> validationDebit(long IdBANKCARD);
}
