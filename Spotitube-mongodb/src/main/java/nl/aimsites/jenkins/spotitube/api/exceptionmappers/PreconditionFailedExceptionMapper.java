package nl.aimsites.jenkins.spotitube.api.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;

@Provider
public class PreconditionFailedExceptionMapper implements ExceptionMapper<PreconditionFailedException> {
    @Override
    public Response toResponse(PreconditionFailedException e) {
        return Response.status(412)
                .entity("Precondition failed: " + e.getMessage())
                .build();
    }
}
