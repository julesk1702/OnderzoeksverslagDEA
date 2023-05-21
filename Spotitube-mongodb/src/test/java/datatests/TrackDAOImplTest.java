package datatests;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.data.daoimpl.TrackDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TrackDAOImplTest {

    TrackDAOImpl trackDAOImpl;

    @BeforeEach
    public void setup() {
        trackDAOImpl = new TrackDAOImpl();
    }

    @Test
    public void testReadTrackInSpecificPlaylist() {
        // Arrange
        int playlistId = 1;
        // Act
        TrackCollectionDTO result = trackDAOImpl.read(playlistId);
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testReadAllTracksExceptOne() {
        // Arrange
        int exemptId = 1;
        // Act
        TrackCollectionDTO result = trackDAOImpl.readAll(exemptId);
        TrackCollectionDTO result2 = trackDAOImpl.read(1);
        // Assert
        assertNotNull(result);
        // assert that result doesn't contain any tracks included in result2
        for (TrackDTO track : result2.getTracks()) {
            TrackDTO[] tracks = result.getTracks();
            assertEquals(false, Arrays.stream(tracks).anyMatch(track::equals));
        }
    }

    @Test
    public void testAddTrackToPlaylist() {
        // Arrange
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setTitle("test");
        trackDTO.setPerformer("test");
        trackDTO.setDuration(1);
        trackDTO.setAlbum("test");
        trackDTO.setPlaycount(1);
        trackDTO.setPublicationDate("test");
        trackDTO.setDescription("test");
        trackDTO.setOfflineAvailable(true);
        int playlistId = 1;
        // Act
        trackDAOImpl.add(playlistId, trackDTO);
        TrackCollectionDTO result = trackDAOImpl.read(playlistId);
        // Assert
        assertNotNull(result);
        assertEquals(true, Arrays.stream(result.getTracks()).anyMatch(track -> track.getId() == trackDTO.getId()));
    }

    @Test
    public void testDeleteTrackFromPlaylist() {
        // Arrange
        int playlistId = 1;
        int trackId = 1;
        // Act
        trackDAOImpl.remove(playlistId, trackId);
        TrackCollectionDTO result = trackDAOImpl.read(playlistId);
        // Assert
        assertNotNull(result);
        assertEquals(false, Arrays.stream(result.getTracks()).anyMatch(track -> track.getId() == trackId));
    }

    @Test
    public void testCalculateTracksLengthInSeconds() {
        // Arrange
        // Act
        long result = trackDAOImpl.calculateTrackLengthInSeconds();
        // Assert
        assertNotNull(result);
        assertEquals(true, result > 0);
    }

}
