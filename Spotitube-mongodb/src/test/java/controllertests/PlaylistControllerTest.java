package controllertests;

import nl.aimsites.jenkins.spotitube.controller.PlaylistControllerImpl;
import nl.aimsites.jenkins.spotitube.controller_contract.PlaylistController;
import nl.aimsites.jenkins.spotitube.data_access.PlaylistDAO;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class PlaylistControllerTest {

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private PlaylistController playlistController = new PlaylistControllerImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Read
    @Test
    public void testGetSpecificPlaylist() {
        // Arrange
        String token = "valid-token";
        doReturn("user").when(userDAO).readByToken(token);
        doReturn(true).when(playlistDAO).isOwner(1, "user");
        doReturn(new PlaylistDTO()).when(playlistDAO).read(1);
        // Act
        PlaylistDTO result = playlistController.getSpecificPlaylist(1, token);
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetAllPlaylistsWithValidToken() {
        // Arrange
        String token = "valid-token";
        PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistCollectionDTO.setPlaylists(new PlaylistDTO[]{playlistDTO});
        doReturn("user").when(userDAO).readByToken(token);
        doReturn(true).when(playlistDAO).isOwner(1, "user");
        doReturn(1).when(trackDAO).calculateTrackLengthInSeconds();
        doReturn(playlistCollectionDTO).when(playlistDAO).read();
        // Act
        PlaylistCollectionDTO result = playlistController.getAllPlaylists(token);
        // Assert
        assertEquals(1, result.getLength());
        assertEquals(1, result.getPlaylists()[0].getId());
        assertEquals(true, result.getPlaylists()[0].isOwner());
    }

    @Test
    public void testGetAllPlaylistsWithInvalidToken() {
        // Arrange
        String token = "invalid-token";
        doReturn(null).when(userDAO).readByToken(token);
        // Act
        PlaylistCollectionDTO result = playlistController.getAllPlaylists(token);
        // Assert
        assertEquals(null, result);
    }

    // Create
    @Test
    public void testCreatePlaylistWithValidToken() {
        // Arrange
        String token = "valid-token";
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("playlist");
        doReturn("user").when(userDAO).readByToken(token);
        doReturn(1).when(playlistDAO).create(playlistDTO.getName());
        PlaylistController playlistController = new PlaylistControllerImpl(playlistDAO, userDAO, trackDAO);
        PlaylistController spyPlaylistController = spy(playlistController);
        doReturn(new PlaylistCollectionDTO()).when(spyPlaylistController).getAllPlaylists(token);
        // Act
        PlaylistCollectionDTO result = spyPlaylistController.createPlaylist(playlistDTO, token);
        // Assert
        assertNotNull(result);
    }

    // Update
    @Test
    public void testUpdatePlaylistWithValidTokenAndUserIsOwner() {
        // Arrange
        String token = "valid-token";
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("playlist");
        playlistDTO.setId(1);

        doReturn("user").when(userDAO).readByToken(token);
        doReturn(true).when(playlistDAO).isOwner(1, "user");

        PlaylistController playlistController = new PlaylistControllerImpl(playlistDAO, userDAO, trackDAO);
        PlaylistController spyPlaylistController = spy(playlistController);
        doReturn(new PlaylistCollectionDTO()).when(spyPlaylistController).getAllPlaylists(token);

        // Act
        PlaylistCollectionDTO result = spyPlaylistController.createPlaylist(playlistDTO, token);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testUpdatePlaylistWithValidTokenAndUserIsNotOwner() {
        // Arrange
        String token = "valid-token";
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("playlist");
        playlistDTO.setId(1);

        doReturn("user").when(userDAO).readByToken(token);
        doReturn(false).when(playlistDAO).isOwner(1, "user");

        // Act
        PlaylistCollectionDTO result = playlistController.updatePlaylist(playlistDTO, token);

        // Assert
        assertEquals(null, result);
    }

    // Delete
    @Test
    public void testDeletePlaylist() {
        // Arrange
        String token = "valid-token";
        int id = 1;

        PlaylistController playlistController = new PlaylistControllerImpl(playlistDAO, userDAO, trackDAO);
        PlaylistController spyPlaylistController = spy(playlistController);
        doReturn("user").when(userDAO).readByToken(token);
        doReturn(new PlaylistCollectionDTO()).when(spyPlaylistController).getAllPlaylists(token);

        // Act
        PlaylistCollectionDTO result = spyPlaylistController.deletePlaylist(id, token);

        // Assert
        assertNotNull(result);
    }

}
