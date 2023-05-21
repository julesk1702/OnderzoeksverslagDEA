package nl.aimsites.jenkins.spotitube.data_access;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;

public interface PlaylistDAO {

    /**
     * Creates a new playlist
     * @param name Name of the playlist to create
     * @return ID of the created playlist
     * @throws DataAccessException Read or write error
     */
    int create(String name) throws DataAccessException;

    /**
     * Deletes a playlist
     * @param id ID of the playlist to delete
     * @return List of playlists
     * @throws DataAccessException Read or write error
     */
    void delete(int id) throws DataAccessException;

    /**
     * Updates a playlist
     * @param name New name of the playlist
     * @param id ID of the playlist to update
     * @return List of playlists
     * @throws DataAccessException Read or write error
     */
    void update(String name, int id) throws DataAccessException;

    /**
     * Reads all playlists
     * @return List of all playlists
     * @throws DataAccessException Read or write error
     */
    PlaylistCollectionDTO read() throws DataAccessException;

    /**
     * Reads a playlist by ID
     * @param id ID of the playlist to read
     * @return The playlist with the given ID
     * @throws DataAccessException Read or write error
     */
    PlaylistDTO read(int id) throws DataAccessException;

    /**
     * Checks if the user is the owner of the playlist
     * @param id ID of the playlist
     * @param user Username of the user
     * @return True if the user is the owner of the playlist
     * @throws DataAccessException Read or write error
     */
    Boolean isOwner(int id, String user) throws DataAccessException;

}
