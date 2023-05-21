package controllertests;

import nl.aimsites.jenkins.spotitube.controller.UserControllerImpl;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

public class UserControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserController userController = new UserControllerImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Read
    @Test
    public void testGetUserByToken() {
        // Arrange
        String token = "token";
        doReturn("user").when(userDAO).readByToken(token);
        // Act
        String user = userController.getUserByToken(token);
        // Assert
        assertEquals("user", user);
    }

    @Test
    public void testGetTokenByUserWithNonEmptyToken() {
        // Arrange
        String user = "user";
        doReturn(new LoginDTO("token", "user", "password")).when(userDAO).readByUser(user);
        // Act
        LoginDTO loginDTO = userController.getUserByUsername(user);
        // Assert
        assertEquals("user", loginDTO.getUser());
        assertEquals("password", loginDTO.getPassword());
        assertEquals("token", loginDTO.getToken());
    }

    @Test
    public void testGetTokenByUserWithEmptyToken() {
        // Arrange
        String user = "user";
        doReturn(new LoginDTO(null, "user", "password")).when(userDAO).readByUser(user);
        doReturn("token").when(userDAO).alterToken(eq(user), anyString());
        // Act
        LoginDTO loginDTO = userController.getUserByUsername(user);
        // Assert
        assertEquals("user", loginDTO.getUser());
        assertEquals("password", loginDTO.getPassword());
        assertNotNull(loginDTO.getToken());
    }

    @Test
    public void testValidateUserCredentialsWithCorrectCredentials() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("token", "user", "password");
        doReturn(loginDTO).when(userDAO).readByUser("user");
        // Act
        boolean result = userController.validateUserCredentials(new LoginRequestDTO("user", "password"));
        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testValidateUserCredentialsWithIncorrectCredentials() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("token", "user", "password");
        doReturn(loginDTO).when(userDAO).readByUser("user");
        // Act
        boolean result = userController.validateUserCredentials(new LoginRequestDTO("user", "wrongPassword"));
        // Assert
        assertEquals(false, result);
    }

}
