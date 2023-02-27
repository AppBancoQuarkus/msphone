package com.nttd.billeteradig.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import com.nttd.billeteradig.dto.ValidationCardDto;
import com.nttd.billeteradig.entity.PhoneEntity;
import com.nttd.billeteradig.service.PhoneService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
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
     Logger logger;

  

     @GET
     @Operation(summary = "Obtener todos los registros de billetera digital", description = "Permite obtener registros de la billetera digital")
     public Uni<List<PhoneEntity>> getAllPhone() {
          logger.info("Iniciando el metodo getAllPhone - Resource.");
          return phoneService.getAllPhone();
     }

     @GET
     @Path("{telephone}")
     @Operation(summary = "Obtener usuario por numero de telefono", description = "Permite buscar al usuario por el campo telephone")
     public Uni<PhoneEntity> findByTelephone(@PathParam("telephone") String telephone) {
          logger.info("Iniciando metodo buscar por telephone - Resource");
          return phoneService.findPhoneByTelephone(telephone);
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
          logger.info("Iniciando metodo update usuario wallet - Resource");
          return phoneService.update(id, phoneEntity);
     }

     @DELETE
     @Path("{id}")
     @Operation(summary = "Eliminar usuario billetera digital", description = "Permite una eliminacion logica del usuario mas no de la bd")
     public Uni<PhoneEntity> deletePhone(@PathParam("id") String id) {
          logger.info("Iniciando metodo delete usuario wallet - Resource");
          return phoneService.delete(id);
     }

     @GET
     @Path("/test/{id}")
     Uni<ValidationCardDto> getValidationCard(@PathParam("id") String cardNumber){
          return phoneService.getValidationCard(cardNumber);
     }

}
