package com.nttd.billeteradig.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import com.nttd.billeteradig.entity.PhoneEntity;
import com.nttd.billeteradig.service.IncrementService;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/phone")
public class PhoneResource {

     @Inject
     PhoneService phoneService;

     @Inject
     IncrementService incrementService;

     @Inject
     Logger logger;

     @GET
     @Path("/redis/{key}")
     @Operation(summary = "Agregando redis del microservicio", description = "Permite agregar redis para este microservicio")
     public Uni<String> get(@PathParam("key") String key) {
          logger.info("Iniciando el metodo redis get - Resource.");
          return incrementService.get(key);
     }

     @GET
     @Operation(summary = "Obtener todos los registros de billetera digital", description = "Permite obtener registros de la billetera digital")
     public Uni<String> getAllPhone() {
          logger.info("Iniciando el metodo getAllPhone - Resource.");
          return phoneService.getAllPhone();
     }

     @GET
     @Path("{idUsuario}")
     @Operation(summary = "Obtener usuario por idUsuario", description = "Permite buscar al usuario por el campo idUsuario")
     public Uni<PhoneEntity> findByidUsuario(@PathParam("idUsuario") long idUsuario) {
          logger.info("Iniciando metodo buscar por idUsuario - Resource");
          return phoneService.findPhoneByIdCustomer(idUsuario);
     }

     @POST
     @Operation(summary = "Agregar usuario billetera digital", description = "Permite agregar nuevos usuarios a nuestra billetera digital")
     public Uni<PhoneEntity> addPhone(PhoneEntity phoneEntity) {
          logger.info("Iniciando metodo agregar usuario wallet - Resource");
          return phoneService.addPhone(phoneEntity);
     }

     @PUT
     @Path("{ id }")
     @Operation(summary = "Actualizar usuario billetera digital", description = "Permite actualizar ciertos campos del usuario -billetera digital")
     public Uni<PhoneEntity> updatePhone(@PathParam("id") String id, PhoneEntity phoneEntity) {
          logger.info("Iniciando metodo update usuaario waller - Resource");
          return phoneService.update(id, phoneEntity);
     }

}
