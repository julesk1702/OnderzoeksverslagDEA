package nl.aimsites.jenkins.spotitube.controller_contract;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginRequestDTO;

public interface UserController {

    /**
     * Get the user's username from the token
     * @param token The token of the user
     * @return The user
     */
    String getUserByToken(String token);

    /**
     * Get the token from the user
     * @param username The user
     * @return {@link LoginDTO} Containing the user and the token, if the token is null a new token is created
     */
    LoginDTO getUserByUsername(String username);

    /**
     * Validate the user credentials by checking if the user exists and if the password is correct
     * @param user The user
     * @return True if the user credentials are valid
     */
    boolean validateUserCredentials(LoginRequestDTO user);

    /**
     * Set the token for the user and update the token in the database
     * @param user The user
     * @return The token
     */
    String setToken(LoginRequestDTO user);

    /**
     * Generate a token
     * @return The token
     */
    String generateToken();

}
