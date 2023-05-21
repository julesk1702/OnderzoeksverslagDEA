package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Login request data transfer object
 */
public class LoginRequestDTO {

    String user;
    String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
