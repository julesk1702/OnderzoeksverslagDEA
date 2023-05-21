package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Playlist collection data transfer object
 * Contains a list of playlists and the total length of all playlists
 */
public class PlaylistCollectionDTO {

    private PlaylistDTO[] playlists;
    private int length;

    public PlaylistCollectionDTO() {
    }

    public PlaylistCollectionDTO(PlaylistDTO[] playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public PlaylistDTO[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(PlaylistDTO[] playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
