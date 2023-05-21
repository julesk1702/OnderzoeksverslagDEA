package nl.aimsites.jenkins.spotitube.controller_contract;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;

public interface TrackController {

    /**
     * Add a track to a playlist
     * @param playlistId The id of the playlist to add the track to
     * @param track The track to add
     * @return A {@link TrackCollectionDTO} containing all tracks in the playlist
     */
    TrackCollectionDTO addTrackToPlaylist(int playlistId, TrackDTO track);

    /**
     * Delete a track from a playlist
     * @param playlistId The id of the playlist to delete the track from
     * @param trackId The id of the track to delete
     * @return A {@link TrackCollectionDTO} containing all tracks in the playlist
     */
    TrackCollectionDTO deleteTrackFromPlaylist(int playlistId, int trackId);

    /**
     * Get all tracks from a playlist
     * @param playlistId The id of the playlist to get the tracks from
     * @return A {@link TrackCollectionDTO} containing all tracks in the playlist
     */
    TrackCollectionDTO getAllTracksFromPlaylist(int playlistId);

    /**
     * Get all tracks not in a playlist
     * @param playlistId The id of the playlist to get the tracks not in
     * @return A {@link TrackCollectionDTO} containing all tracks not in the playlist
     */
    TrackCollectionDTO getAllTracksNotInPlaylist(int playlistId);

}
