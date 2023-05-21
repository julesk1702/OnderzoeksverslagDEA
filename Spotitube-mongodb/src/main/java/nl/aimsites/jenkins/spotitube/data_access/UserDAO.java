package nl.aimsites.jenkins.spotitube.data_access;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;

public interface UserDAO {

    /**
     * Creates a token for the user and adds it to the database
     * @param user Username
     * @param token New token
     * @return Username, password and token
     * @throws DataAccessException Read or write error
     */
    String alterToken(String user, String token) throws DataAccessException;

    /**
     * Reads the username for the given token
     * @param token Login token
     * @return Username
     * @throws DataAccessException Read or write error
     */
    String readByToken(String token) throws DataAccessException;

    /**
     * Read the username, password and token for the given username
     * @param user Username
     * @return Username, password and token
     * @throws DataAccessException Read or write error
     */
    LoginDTO readByUser(String user) throws DataAccessException;

    /**
     * Adds a playlist to the user's playlists
     * @param user Username
     * @param playlistId ID of the playlist
     * @throws DataAccessException Read or write error
     */
    void addPlaylistOwnership(String user, int playlistId) throws DataAccessException;

    /**
     * Deletes a playlist from the user's playlists
     * @param user Username
     * @param playlistId ID of the playlist
     * @throws DataAccessException Read or write error
     */
    void deletePlaylistOwnership(String user, int playlistId) throws DataAccessException;

}
