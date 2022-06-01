package org.eclipse.mvd.registration.api.enrollment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@OpenAPIDefinition
@Tag(name = "Enrollment")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@Path("/enroll")
public class EnrollmentApiController {

    @GET
    public Response ping() {
        return Response.ok("pong").build();
    }
}
