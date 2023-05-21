package nl.aimsites.jenkins.spotitube.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginRequestDTO;

@Default
@RequestScoped
@Named("userController")
public class UserControllerImpl implements UserController {

    private UserDAO userDAO;

    @Inject
    public UserControllerImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserControllerImpl() {
        this(null);
    }

    @Override
    public String getUserByToken(String token) {
        return userDAO.readByToken(token);
    }

    @Override
    public LoginDTO getUserByUsername(String user) {
        LoginDTO userInfo = userDAO.readByUser(user);
        if (userInfo.getToken() == null) {
            userInfo.setToken(setToken(new LoginRequestDTO(user, userInfo.getPassword())));
        }
        return userInfo;
    }

    @Override
    public boolean validateUserCredentials(LoginRequestDTO user) {
        LoginDTO loginDTO = userDAO.readByUser(user.getUser());
        return loginDTO != null && loginDTO.getPassword().equals(user.getPassword());
    }

    @Override
    public String setToken(LoginRequestDTO user) {
        String token = generateToken();
        return userDAO.alterToken(user.getUser(), token);
    }

    @Override
    public String generateToken() {
        return String.format("%04d-%04d-%04d", (int) (Math.random() * 10000), (int) (Math.random() * 10000), (int) (Math.random() * 10000));
    }

}
