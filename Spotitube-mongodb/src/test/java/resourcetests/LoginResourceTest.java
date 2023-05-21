package resourcetests;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.api.LoginResource;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginRequestDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginResponseDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginResourceTest {

    @InjectMocks
    LoginResource loginResource;

    @Mock
    LoginRequestDTO loginRequest;

    @Mock
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginRequest = new LoginRequestDTO();
    }

    @Test
    public void testLoginWithValidCredentialsReturns200Response() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser("admin");
        loginDTO.setPassword("admin");
        loginDTO.setToken("token");
        loginRequest.setUser("admin");
        loginRequest.setPassword("admin");
        when(userController.getUserByUsername("admin")).thenReturn(loginDTO);
        doReturn(true).when(userController).validateUserCredentials(loginRequest);

        // Act
        Response response = loginResource.login(loginRequest);

        // Assert
        assertEquals(200, response.getStatus());
        LoginResponseDTO loginResponse = (LoginResponseDTO) response.getEntity();
        assertEquals("admin", loginResponse.getUser());
    }

    @Test
    public void testLoginWithInvalidCredentialsReturns404Response() {
        // Mock
        when(userController.getUserByUsername("admin")).thenReturn(new LoginDTO("token", "admin", "admin"));
        doReturn(false).when(userController).validateUserCredentials(new LoginRequestDTO("admin", "wrong!!!!!!!!!!!!!"));

        // Arrange
        loginRequest.setUser("admin");
        loginRequest.setPassword("wrong!!!!!!!!!!!!!");

        // Act & Assert
        try {
            loginResource.login(loginRequest);
            fail("Expected exception was not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    @Test
    public void testLoginWithNullCredentialsReturns400Response() {
        // Mock
        when(userController.getUserByUsername(null)).thenReturn(null);

        // Arrange
        loginRequest.setUser(null);
        loginRequest.setPassword(null);

        // Act & Assert
        assertThrows(PreconditionFailedException.class, () -> {
            loginResource.login(loginRequest);
        });
    }
}