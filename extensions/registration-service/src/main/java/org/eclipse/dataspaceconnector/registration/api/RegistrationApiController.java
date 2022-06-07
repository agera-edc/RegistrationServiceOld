package org.eclipse.dataspaceconnector.registration.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.dataspaceconnector.registration.api.model.Participant;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;

import java.util.List;

/**
 * Registration Service API controller to manage dataspace participants.
 */
@Tag(name = "Registry")
@Produces({"application/json"})
@Consumes({"application/json"})
@Path("/registry")
public class RegistrationApiController {

    private final RegistrationService service;
    private final Monitor monitor;

    /**
     * Constructs an instance of {@link RegistrationApiController}
     *
     * @param service   service handling the registration service logic.
     * @param monitor   logging monitor.
     */
    public RegistrationApiController(RegistrationService service, Monitor monitor) {
        this.service = service;
        this.monitor = monitor;
    }

    /**
     * Lists all dataspace participants.
     *
     * @return list of dataspace participants.
     */
    @Path("/participants")
    @GET
    public List<Participant> listParticipants() {
        monitor.info("List all participants of the dataspace.");
        return service.listParticipants();
    }
}
