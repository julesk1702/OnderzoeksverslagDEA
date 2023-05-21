package nl.aimsites.jenkins.spotitube.controller_contract;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;

public interface PlaylistController {

    /**
     * Get all playlists, set the owner flag for each playlist and calculate the total length of all tracks
     * @param token The token of the user requesting the playlists
     * @return A {@link PlaylistCollectionDTO} containing all playlists, the owner flag for each playlist and the total length of all tracks
     */
    PlaylistCollectionDTO getAllPlaylists(String token);

    /**
     * Create a new playlist and add it to the user's playlists
     * @param playlist The playlist to create
     * @param token The token of the user creating the playlist
     * @return A {@link PlaylistCollectionDTO} containing all playlists, the owner flag for each playlist and the total length of all tracks
     */
    PlaylistCollectionDTO createPlaylist(PlaylistDTO playlist, String token);

    /**
     * Delete a playlist
     * @param id The id of the playlist to delete
     * @param token The token of the user deleting the playlist
     * @return A {@link PlaylistCollectionDTO} containing all playlists, the owner flag for each playlist and the total length of all tracks
     */
    PlaylistCollectionDTO deletePlaylist(int id, String token);

    /**
     * Update a playlist
     * @param playlist The playlist to update
     * @param token The token of the user updating the playlist
     * @return A {@link PlaylistCollectionDTO} containing all playlists, the owner flag for each playlist and the total length of all tracks
     */
    PlaylistCollectionDTO updatePlaylist(PlaylistDTO playlist, String token);

    /**
     * Get a specific playlist and set the owner flag
     * @param id The id of the playlist to get
     * @param token The token of the user requesting the playlist
     * @return The requested playlist
     */
    PlaylistDTO getSpecificPlaylist(int id, String token);

}
