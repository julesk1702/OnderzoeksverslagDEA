package datatests;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.data.daoimpl.UserDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOImplTest {

    UserDAOImpl userDAOImpl;

    @BeforeEach
    public void setup() {
        userDAOImpl = new UserDAOImpl();
    }

    @Test
    public void testReadUserByTokenWithCorrectToken() {
        // Arrange
        String token = "1234-1234-1234";
        // Act
        String result = userDAOImpl.readByToken(token);
        // Assert
        assertNotNull(result);
        assertEquals("admin", result);
    }

    @Test
    public void testReadUserByTokenWithIncorrectToken() {
        // Arrange
        String token = "WRONG-TOKEN";
        // Act
        String result = userDAOImpl.readByToken(token);
        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testReadUserByUserWithCorrectUser() {
        // Arrange
        String user = "admin";
        // Act
        LoginDTO result = userDAOImpl.readByUser(user);
        // Assert
        assertNotNull(result);
        assertEquals("admin", result.getUser());
    }

    @Test
    public void testReadUserByUserWithIncorrectUser() {
        // Arrange
        String user = "WRONG-USER";
        // Act
        LoginDTO result = userDAOImpl.readByUser(user);
        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testAddPlaylistOwnership() {
        // Arrange
        String user = "admin";
        int playlistId = 1;
        // Act
        userDAOImpl.addPlaylistOwnership(user, playlistId);
        // Assert
        // No assert needed, if no exception is thrown, the test is passed
    }

    @Test
    public void testDeletePlaylistOwnership() {
        // Arrange
        String user = "admin";
        int playlistId = 1;
        // Act
        userDAOImpl.deletePlaylistOwnership(user, playlistId);
        // Assert
        // No assert needed, if no exception is thrown, the test is passed
    }

    @Test
    public void testCreateUserToken() {
        // Arrange
        String user = "test";
        String token = "test-test-test";
        // Act
        String result = userDAOImpl.alterToken(user, token);
        // Assert
        assertNotNull(result);
    }

}
