package nl.aimsites.jenkins.spotitube.data_access;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;

public interface TrackDAO {

    /**
     * Reads all tracks for a playlist
     * @param playlistId Playlist id
     * @return List of tracks
     * @throws DataAccessException Read or write error
     */
    TrackCollectionDTO read(int playlistId) throws DataAccessException;

    /**
     * Reads all tracks, except for the tracks in the exemptPlaylist
     * @param exemptPlaylist Playlist id to exclude
     * @return List of tracks
     * @throws DataAccessException Read or write error
     */
    TrackCollectionDTO readAll(int exemptPlaylist) throws DataAccessException;

    /**
     * Adds a track to a playlist
     * @param playlistId Playlist id to add track to
     * @param track Track to add
     * @return List of tracks
     * @throws DataAccessException Read or write error
     */
    void add(int playlistId, TrackDTO track) throws DataAccessException;

    /**
     * Deletes a track from a playlist
     * @param playlistId Playlist id to delete track from
     * @param trackId Track id to delete
     * @return List of tracks
     * @throws DataAccessException Read or write error
     */
    void remove(int playlistId, int trackId) throws DataAccessException;

    /**
     * Deletes all tracks from a playlist
     * @param playlistId Playlist id to delete tracks from
     * @throws DataAccessException Read or write error
     */
    void removeAll(int playlistId) throws DataAccessException;

    /**
     * Gets the total length of all tracks in all playlists
     * @return Total length of all tracks in all playlists
     * @throws DataAccessException Read or write error
     */
    long calculateTrackLengthInSeconds() throws DataAccessException;

}
