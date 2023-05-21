package datatests;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.data.daoimpl.PlaylistDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class PlaylistDAOImplTest {

    PlaylistDAOImpl playlistDAOImpl;

    @BeforeEach
    public void setup() {
        playlistDAOImpl = new PlaylistDAOImpl();
    }

    @Test
    public void testReadAllPlaylists() {
        // Arrange
        // Act
        PlaylistCollectionDTO result = playlistDAOImpl.read();
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testReadSpecificPlaylist() {
        // Arrange
        int id = 1;
        // Act
        PlaylistDTO result = playlistDAOImpl.read(id);
        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testIsOwner() {
        // Arrange
        int id = 1;
        String user = "user";
        // Act
        boolean result = playlistDAOImpl.isOwner(id, user);
        // Assert
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testCreatePlaylist() {
        // Arrange
        // Act
        int result = playlistDAOImpl.create("test");
        playlistDAOImpl.delete(result);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeletePlaylist() {
        // Arrange
        int id = playlistDAOImpl.create("test");
        // Act
        playlistDAOImpl.delete(id);
        PlaylistDTO result = playlistDAOImpl.read(id);
        // Assert
        assertNull(result);
    }

    @Test
    public void testUpdatePlaylist() {
        // Arrange
        int id = playlistDAOImpl.create("test");
        // Act
        playlistDAOImpl.update("test2", id);
        PlaylistDTO result = playlistDAOImpl.read(id);
        // Assert
        assertNotNull(result);
        assertEquals("test2", result.getName());
        playlistDAOImpl.delete(id);
    }

}
