package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Login request data transfer object
 */
public class LoginResponseDTO {

    private String token;
    private String user;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
