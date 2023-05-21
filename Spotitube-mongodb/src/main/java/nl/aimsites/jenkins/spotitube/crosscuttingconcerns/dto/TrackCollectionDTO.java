package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Track collection data transfer object
 * Contains a list of tracks
 */
public class TrackCollectionDTO {
    private TrackDTO[] tracks;

    public TrackCollectionDTO() {
    }

    public TrackCollectionDTO(TrackDTO[] tracks) {
        this.tracks = tracks;
    }

    public TrackDTO[] getTracks() {
        return tracks;
    }

    public void setTracks(TrackDTO[] tracks) {
        this.tracks = tracks;
    }
}
