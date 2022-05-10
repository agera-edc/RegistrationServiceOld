package org.eclipse.mvd.registration.api.enrollment;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Produces({ "application/json" })
@Consumes({ "application/json" })
@Path("/enroll")
public class EnrollmentApiController implements EnrollmentApi {

    @GET
    public Response ping() {
        return Response.ok("pong").build();
    }
}
