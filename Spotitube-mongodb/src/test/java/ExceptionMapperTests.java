import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.api.exceptionmappers.*;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionMapperTests {

    @Test
    public void testDataAccessExceptionMapperReturns500Response() {
        DataAccessExceptionMapper mapper = new DataAccessExceptionMapper();
        DataAccessException ex = new DataAccessException("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(500, response.getStatus());
        Assertions.assertEquals("An error occurred while accessing data: test exception", response.getEntity());
    }

    @Test
    public void testPreconditionFailedExceptionMapperReturns412Response() {
        PreconditionFailedExceptionMapper mapper = new PreconditionFailedExceptionMapper();
        PreconditionFailedException ex = new PreconditionFailedException("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(412, response.getStatus());
        Assertions.assertEquals("Precondition failed: test exception", response.getEntity());
    }

    @Test
    public void testBadRequestExceptionMapperReturns400Response() {
        BadRequestExceptionMapper mapper = new BadRequestExceptionMapper();
        BadRequestException ex = new BadRequestException("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(400, response.getStatus());
        Assertions.assertEquals("Bad request: test exception", response.getEntity());
    }

    @Test
    public void testGlobalExceptionMapperReturns500Response() {
        GlobalExceptionMapper mapper = new GlobalExceptionMapper();
        Exception ex = new Exception("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(500, response.getStatus());
        Assertions.assertEquals("An unknown error occurred: test exception", response.getEntity());
    }

    @Test
    public void testNotAuthorizedExceptionMapperReturns401Response() {
        NotAuthorizedExceptionMapper mapper = new NotAuthorizedExceptionMapper();
        NotAuthorizedException ex = new NotAuthorizedException("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(401, response.getStatus());
    }

    @Test
    public void testNotFoundExceptionMapperReturns404Response() {
        NotFoundExceptionMapper mapper = new NotFoundExceptionMapper();
        NotFoundException ex = new NotFoundException("test exception");

        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(404, response.getStatus());
        Assertions.assertEquals("Not found: test exception", response.getEntity());
    }

}
