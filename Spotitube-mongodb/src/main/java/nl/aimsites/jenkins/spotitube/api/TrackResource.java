package nl.aimsites.jenkins.spotitube.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.controller_contract.TrackController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;

import static jakarta.ws.rs.core.Response.status;

@Path("/tracks")
@RequestScoped
public class TrackResource {

    private final TrackController trackController;

    @Inject
    public TrackResource(TrackController trackController) {
        this.trackController = trackController;
    }

    public TrackResource() {
        this(null);
    }

    /**
     * Get all tracks except the ones in the current playlist
     * @param token The token of the user
     * @param playlistId The ID of the playlist to exempt
     * @return A list of all non-excluded tracks in JSON format
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        if (playlistId == 0) throw new PreconditionFailedException("No playlist ID provided");
        TrackCollectionDTO trackCollectionDTO = trackController.getAllTracksNotInPlaylist(playlistId);
        return status(200).entity(trackCollectionDTO).build();
    }

}
