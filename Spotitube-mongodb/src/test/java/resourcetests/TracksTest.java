package resourcetests;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.api.PlaylistResource;
import nl.aimsites.jenkins.spotitube.api.TrackResource;
import nl.aimsites.jenkins.spotitube.controller_contract.PlaylistController;
import nl.aimsites.jenkins.spotitube.controller_contract.TrackController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


public class TracksTest {

    private final String token = "1234-1234-1234";

    @Mock
    private PlaylistController playlistController;

    @Mock
    private TrackController trackController;

    @InjectMocks
    TrackResource tracksResource;

    @InjectMocks
    PlaylistResource playlistResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Read all
    @Test
    public void testGetAllTracksExcludingOnePlaylist() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        doReturn(trackCollectionDTO).when(trackController).getAllTracksNotInPlaylist(1);
        // Act
        Response response = tracksResource.getTracks(token, 1);
        // Assert
        assertEquals(200, response.getStatus());
    }

    // Read specific
    @Test
    public void testGetSpecificTrackWithValidId() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        doReturn(trackCollectionDTO).when(trackController).getAllTracksFromPlaylist(1);
        // Act
        Response response = playlistResource.getTracks(token, 1);
        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetSpecificTrackWithInvalidId() {
        // Arrange
        doReturn(null).when(trackController).getAllTracksFromPlaylist(1);

        // Act & Assert
        try {
            playlistResource.getTracks(token, 1);
            fail("Expected exception not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    // Create
    @Test
    public void testCreateTrackWithValidPlaylistIdAndValidTrackDTO() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("test");
        trackDTO.setDuration(1);
        PlaylistDTO playlistDTO = new PlaylistDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(trackCollectionDTO).when(trackController).addTrackToPlaylist(1, trackDTO);
        // Act
        Response response = playlistResource.addTrack(token, 1, trackDTO);
        // Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreateTrackWithInvalidPlaylistIdAndValidTrackDTO() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("test");
        trackDTO.setDuration(1);

        doReturn(null).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(trackCollectionDTO).when(trackController).addTrackToPlaylist(1, trackDTO);

        // Act & Assert
        try {
            playlistResource.addTrack(token, 1, trackDTO);
            fail("Expected exception not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    @Test
    public void testCreateTrackWithValidPlaylistIdAndInvalidTrackDTO() {
        // Arrange
        TrackDTO trackDTO = new TrackDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(null).when(trackController).addTrackToPlaylist(1, trackDTO);

        // Act & Assert
        assertThrows(PreconditionFailedException.class, () -> {
            playlistResource.addTrack(token, 1, trackDTO);
        });
    }

    // Delete
    @Test
    public void testDeleteTrackWithValidPlaylistIdAndValidTrackId() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(trackCollectionDTO).when(trackController).deleteTrackFromPlaylist(1, 1);
        // Act
        Response response = playlistResource.deleteTrack(token, 1, 1);
        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeleteTrackWithInvalidPlaylistIdAndValidTrackId() {
        // Arrange
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        doReturn(null).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(trackCollectionDTO).when(trackController).deleteTrackFromPlaylist(1, 1);

        // Act & Assert
        try {
            playlistResource.deleteTrack(token, 1, 1);
            fail("Expected exception not thrown");
        } catch (WebApplicationException e) {
            assertEquals(404, e.getResponse().getStatus());
        }
    }

    @Test
    public void testDeleteTrackWithValidPlaylistIdAndInvalidTrackId() {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        doReturn(playlistDTO).when(playlistController).getSpecificPlaylist(1, token);
        doReturn(null).when(trackController).deleteTrackFromPlaylist(1, 1);
        // Act & Assert
        assertThrows(PreconditionFailedException.class, () -> {
            playlistResource.deleteTrack(token, 1, 0);
        });
    }

}
