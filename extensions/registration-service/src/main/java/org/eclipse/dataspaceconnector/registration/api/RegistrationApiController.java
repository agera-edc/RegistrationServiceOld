package org.eclipse.dataspaceconnector.registration.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.dataspaceconnector.registration.api.model.Participant;

import java.util.List;

/**
 * Simple API controller to test service is available.
 */
@Tag(name = "Registry")
@Produces({"application/json"})
@Consumes({"application/json"})
@Path("/registry")
public class RegistrationApiController {

    private RegistrationService service;

    public RegistrationApiController(RegistrationService service) {
        this.service = service;
    }

    @Path("/participants")
    @GET
    public List<Participant> listParticipants() {
        return service.listParticipants();
    }
}
