package controllertests;

import nl.aimsites.jenkins.spotitube.controller.TrackControllerImpl;
import nl.aimsites.jenkins.spotitube.controller_contract.TrackController;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

public class TrackControllerTest {

    @Mock
    private TrackDAO trackDAO;

    @InjectMocks
    private TrackController trackController = new TrackControllerImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doReturn(new TrackCollectionDTO()).when(trackDAO).read(1);
    }

    @Test
    public void testAddTrackToPlaylist() {
        // Assert
        // Act
        TrackCollectionDTO result = trackController.addTrackToPlaylist(1, new TrackDTO());
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteTrackFromPlaylist() {
        // Assert
        // Act
        TrackCollectionDTO result = trackController.deleteTrackFromPlaylist(1, 1);
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetAllTracksFromPlaylist() {
        // Assert
        // Act
        TrackCollectionDTO result = trackController.getAllTracksFromPlaylist(1);
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetAllTracksNotInPlaylist() {
        // Assert
        doReturn(new TrackCollectionDTO()).when(trackDAO).readAll(1);
        // Act
        TrackCollectionDTO result = trackController.getAllTracksNotInPlaylist(1);
        // Assert
        assertNotNull(result);
    }

}
