package resourcetests;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import nl.aimsites.jenkins.spotitube.api.filters.TokenValidationFilter;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TokenValidationFilterTest {

    @Mock
    private ContainerRequestContext requestContext;

    @Mock
    private UriInfo uriInfo;

    @Mock
    private UserController userController;

    @InjectMocks
    private TokenValidationFilter tokenValidationFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(requestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(mock(MultivaluedMap.class));
    }

    @Test
    public void testFilterWithValidToken() {
        String token = "valid_token";
        when(uriInfo.getPath()).thenReturn("/api/somepath");
        when(uriInfo.getQueryParameters().getFirst("token")).thenReturn(token);
        when(userController.getUserByToken(token)).thenReturn("user");

        tokenValidationFilter.filter(requestContext);

        verify(requestContext, never()).abortWith(any(Response.class));
    }

    @Test
    public void testFilterWithInvalidToken() {
        String token = "invalid_token";
        when(uriInfo.getPath()).thenReturn("/api/somepath");
        when(uriInfo.getQueryParameters().getFirst("token")).thenReturn(token);
        when(userController.getUserByToken(token)).thenReturn(null);

        tokenValidationFilter.filter(requestContext);

        verify(requestContext).abortWith(argThat(response ->
                response.getStatus() == 401 && response.getEntity().equals("Invalid token")
        ));
    }

    @Test
    public void testFilterWithNoToken() {
        when(uriInfo.getPath()).thenReturn("/api/login");
        when(uriInfo.getQueryParameters().getFirst("token")).thenReturn(null);

        tokenValidationFilter.filter(requestContext);

        verify(requestContext, never()).abortWith(any(Response.class));
    }

    @Test
    public void testFilterWithEmptyToken() {
        when(uriInfo.getPath()).thenReturn("/api/somepath");
        when(uriInfo.getQueryParameters().getFirst("token")).thenReturn("");

        tokenValidationFilter.filter(requestContext);

        verify(requestContext).abortWith(argThat(response ->
                response.getStatus() == 401 && response.getEntity().equals("No token provided")
        ));
    }
}