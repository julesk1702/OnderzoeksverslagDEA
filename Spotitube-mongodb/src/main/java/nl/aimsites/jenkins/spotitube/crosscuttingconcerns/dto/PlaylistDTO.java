package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Playlist data transfer object
 */
public class PlaylistDTO {

    private int id;
    private String name;
    private boolean owner;
    private TrackDTO[] tracks;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner, TrackDTO[] tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

}
