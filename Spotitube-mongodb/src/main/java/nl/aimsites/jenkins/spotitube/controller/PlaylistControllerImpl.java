package nl.aimsites.jenkins.spotitube.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.controller_contract.PlaylistController;
import nl.aimsites.jenkins.spotitube.data_access.PlaylistDAO;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;

@Default
@RequestScoped
@Named("playlistController")
public class PlaylistControllerImpl implements PlaylistController {

    private PlaylistDAO playlistDAO;
    private UserDAO userDAO;
    private TrackDAO trackDAO;

    @Inject
    public PlaylistControllerImpl(PlaylistDAO playlistDAO, UserDAO userDAO, TrackDAO trackDAO) {
        this.playlistDAO = playlistDAO;
        this.userDAO = userDAO;
        this.trackDAO = trackDAO;
    }

    public PlaylistControllerImpl() {
        this(null, null, null);
    }

    @Override
    public PlaylistCollectionDTO getAllPlaylists(String token) {
        String user = getUserFromToken(token);
        if (user == null) return null;
        PlaylistCollectionDTO playlists = playlistDAO.read();
        for (PlaylistDTO playlist : playlists.getPlaylists()) {
            Boolean isOwner = playlistDAO.isOwner(playlist.getId(), user);
            playlist.setOwner(isOwner);
        }
        playlists.setLength(trackDAO.calculateTrackLengthInSeconds());
        return playlists;
    }

    @Override
    public PlaylistCollectionDTO createPlaylist(PlaylistDTO playlist, String token) {
        String user = getUserFromToken(token);
        if (user == null) return null;
        int playlistId = playlistDAO.create(playlist.getName());
        userDAO.addPlaylistOwnership(user, playlistId);
        return getAllPlaylists(token);
    }

    @Override
    public PlaylistCollectionDTO deletePlaylist(int id, String token) {
        String user = getUserFromToken(token);
        if (user == null) return null;
        userDAO.deletePlaylistOwnership(user, id);
        trackDAO.removeAll(id);
        playlistDAO.delete(id);
        return getAllPlaylists(token);
    }

    @Override
    public PlaylistCollectionDTO updatePlaylist(PlaylistDTO playlist, String token) {
        String user = getUserFromToken(token);
        if (user == null) return null;
        if (!playlistDAO.isOwner(playlist.getId(), user)) return null;
        playlistDAO.update(playlist.getName(), playlist.getId());
        return getAllPlaylists(token);
    }

    @Override
    public PlaylistDTO getSpecificPlaylist(int id, String token) {
        String user = getUserFromToken(token);
        if (user == null) return null;
        PlaylistDTO playlist = playlistDAO.read(id);
        if (playlist == null) return null;
        playlist.setOwner(playlistDAO.isOwner(id, user));
        return playlist;
    }

    /**
     * Get the username associated with the provided token
     * @param token The token to get the username for
     * @return The username associated with the provided token
     */
    private String getUserFromToken(String token) {
        if (token == null) return null;
        return userDAO.readByToken(token);
    }
}
