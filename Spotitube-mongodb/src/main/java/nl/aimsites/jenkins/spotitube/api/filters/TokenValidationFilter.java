package nl.aimsites.jenkins.spotitube.api.filters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import nl.aimsites.jenkins.spotitube.controller_contract.UserController;

@Provider
@RequestScoped
public class TokenValidationFilter implements ContainerRequestFilter {

    @Inject private UserController userController;

    /**
     * Filter all requests to validate the token
     * @param containerRequestContext The request context
     */
    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        try {
            UriInfo uriInfo = containerRequestContext.getUriInfo();
            if (uriInfo.getPath().contains("login")) return;
            String token = containerRequestContext.getUriInfo().getQueryParameters().getFirst("token");
            if (token.length() == 0) throw new Exception("No token provided");
            if (userController.getUserByToken(token) == null) throw new Exception("Invalid token");
        } catch (Exception e) {
            containerRequestContext.abortWith(
                    Response.status(401)
                            .entity(e.getMessage())
                            .build()
            );
        }
    }
}
