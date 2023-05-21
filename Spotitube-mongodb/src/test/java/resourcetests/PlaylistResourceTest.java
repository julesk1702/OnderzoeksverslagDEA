package resourcetests;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.api.PlaylistResource;
import nl.aimsites.jenkins.spotitube.controller_contract.PlaylistController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PlaylistResourceTest {

    private final String token = "1234-1234-1234";

    @Mock
    private PlaylistController playlistController;

    @InjectMocks
    private PlaylistResource playlistResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Read
    @Test
    public void testGetPlaylistsWithValidToken() {
        // Arrange
        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO();
        doReturn(playlistCollectionDTO).when(playlistController).getAllPlaylists(token);
        // Act
        Response response = playlistResource.getPlaylists(token);
        // Assert
        assertEquals(200, response.getStatus());
    }

    // Update
    @Test
    public void testUpdatePlaylistWithInvalidPlaylistId() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("Playlist");
        doReturn(null).when(playlistController).updatePlaylist(playlistDTO, token);
        doReturn(null).when(playlistController).getSpecificPlaylist(-1, token);
        // Act & Assert
        try {
            playlistResource.editPlaylist(token, -1, playlistDTO);
            fail("Expected exception not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    @Test
    public void testUpdatePlaylistWithValidPlaylistId() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("Playlist");
        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(playlistCollectionDTO).when(playlistController).updatePlaylist(playlistDTO, token);
        // Act
        Response response = playlistResource.editPlaylist(token, 1, playlistDTO);
        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testUpdatePlaylistWithoutPlaylistName() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        // Act and Assert
        assertThrows(PreconditionFailedException.class, () -> {
            playlistResource.editPlaylist(token, 1, playlistDTO);
        });
    }

    // Delete
    @Test
    public void testDeletePlaylistWithInvalidPlaylistId() {
        // Arrange
        doReturn(null).when(playlistController).getSpecificPlaylist(-1, token);
        // Act & Assert
        try {
            playlistResource.deletePlaylist(token, -1);
            fail("Expected exception not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    @Test
    public void testDeletePlaylistWithValidPlaylistId() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(playlistCollectionDTO).when(playlistController).deletePlaylist(1, token);
        // Act
        Response response = playlistResource.deletePlaylist(token, 1);
        // Assert
        assertEquals(200, response.getStatus());
    }

    // Create
    @Test
    public void testCreatePlaylistWithValidPlaylist() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("Playlist");
        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO();
        doReturn(playlistCollectionDTO).when(playlistController).createPlaylist(playlistDTO, token);
        // Act
        Response response = playlistResource.addPlaylist(token, playlistDTO);
        // Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreatePlaylistWithoutPlaylistName() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();

        // Act and Assert
        assertThrows(PreconditionFailedException.class, () -> {
            playlistResource.addPlaylist(token, playlistDTO);
        });
    }

}
