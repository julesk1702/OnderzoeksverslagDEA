package nl.aimsites.jenkins.spotitube.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginRequestDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginResponseDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;

import static jakarta.ws.rs.core.Response.status;

@Path("/login")
@RequestScoped
public class LoginResource {

    private final UserController userController;

    @Inject
    public LoginResource(UserController userController) {
        this.userController = userController;
    }

    public LoginResource() {
        this(null);
    }

    /**
     * Login to the application
     * @param req The user and password in JSON format
     * @return The token and user in JSON format
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO req) {
        if (req.getUser() == null || req.getPassword() == null) throw new PreconditionFailedException("No user and/or password provided");
        if (!userController.validateUserCredentials(req)) throw new NotFoundException("Invalid user and/or password");
        LoginDTO login = userController.getUserByUsername(req.getUser());
        return status(200).entity(new LoginResponseDTO(login.getToken(), login.getUser())).build();
    }

}
