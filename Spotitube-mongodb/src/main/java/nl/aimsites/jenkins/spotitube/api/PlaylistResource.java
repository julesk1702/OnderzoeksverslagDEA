package nl.aimsites.jenkins.spotitube.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.aimsites.jenkins.spotitube.controller_contract.PlaylistController;
import nl.aimsites.jenkins.spotitube.controller_contract.TrackController;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.PreconditionFailedException;

import static jakarta.ws.rs.core.Response.status;

@Path("/playlists")
@RequestScoped
public class PlaylistResource {

    private final PlaylistController playlistController;
    private final TrackController trackController;

    @Inject
    public PlaylistResource(PlaylistController playlistController, TrackController trackController) {
        this.playlistController = playlistController;
        this.trackController = trackController;
    }

    public PlaylistResource() {
        this(null, null);
    }

    /**
     * Get all playlists
     * @param token The token of the user
     * @return A list of playlists in JSON format
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        return status(200).entity(playlistController.getAllPlaylists(token)).build();
    }

    /**
     * Get a specific playlist by ID
     * @param token The token of the user
     * @param id The ID of the playlist
     * @return A playlist in JSON format
     */
    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        if (playlistController.getSpecificPlaylist(id, token) == null) throw new NotFoundException("A playlist with that ID does not exist");
        return status(200).entity(playlistController.deletePlaylist(id, token)).build();
    }

    /**
     * Add a playlist to the database for the user (by name)
     * @param token The token of the user
     * @param playlist The playlist to add in JSON format
     * @return The playlist that was added in JSON format
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist) {
        if (playlist.getName() == null) throw new PreconditionFailedException("No playlist name provided");
        return status(201).entity(playlistController.createPlaylist(playlist, token)).build();
    }

    /**
     * Edit a playlist (change name)
     * @param token The token of the user
     * @param id The ID of the playlist
     * @param playlist The edited playlist in JSON format
     * @return The playlist that was edited in JSON format
     */
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlist) {
        if (playlist.getName() == null) throw new PreconditionFailedException("No playlist name provided");
        if (playlistController.getSpecificPlaylist(id, token) == null) throw new NotFoundException("A playlist with that ID does not exist");
        return status(200).entity(playlistController.updatePlaylist(playlist, token)).build();
    }

    /**
     * Get all tracks from a playlist
     * @param token The token of the user
     * @param id The ID of the playlist to get the tracks from
     * @return A list of tracks in JSON format
     */
    @Path("/{id}/tracks")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        TrackCollectionDTO tracks = trackController.getAllTracksFromPlaylist(id);
        if (tracks == null) throw new NotFoundException("No tracks found for that playlist");
        return status(200).entity(tracks).build();
    }

    /**
     * Add a track to a playlist
     * @param token The token of the user
     * @param id The ID of the playlist to add the track to
     * @param tracks The track to add in JSON format
     * @return A list of all tracks in the playlist in JSON format
     */
    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int id, TrackDTO tracks) {
        if (tracks.getTitle() == null) throw new PreconditionFailedException("No track title provided");
        if (playlistController.getSpecificPlaylist(id, token) == null) throw new NotFoundException("A playlist with that ID does not exist");
        return status(201).entity(trackController.addTrackToPlaylist(id, tracks)).build();
    }

    /**
     * Delete a track from a playlist
     * @param token The token of the user
     * @param id The ID of the playlist to delete the track from
     * @param trackId The ID of the track to delete
     * @return All tracks in the playlist in JSON format
     */
    @Path("/{id}/tracks/{trackId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@QueryParam("token") String token, @PathParam("id") int id, @PathParam("trackId") int trackId) {
        if (trackId == 0 || id == 0) throw new PreconditionFailedException("No trackId or playlistId provided");
        if (playlistController.getSpecificPlaylist(id, token) == null) throw new NotFoundException("A playlist with that ID does not exist");
        return status(200).entity(trackController.deleteTrackFromPlaylist(id, trackId)).build();
    }

}
