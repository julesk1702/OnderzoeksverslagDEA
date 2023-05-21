package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto;

/**
 * Login data transfer object
 */
public class LoginDTO {

    private String user;
    private String password;
    private String token;

    public LoginDTO() {
    }

    public LoginDTO(String token, String user, String password) {
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
