package nl.aimsites.jenkins.spotitube.api.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;

@Provider
public class DataAccessExceptionMapper implements ExceptionMapper<DataAccessException> {

    @Override
    public Response toResponse(DataAccessException ex) {
        return Response.status(500)
                .entity("An error occurred while accessing data: " + ex.getMessage())
                .build();
    }

}