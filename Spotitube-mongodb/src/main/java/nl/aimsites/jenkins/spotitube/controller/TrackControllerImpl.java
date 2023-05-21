package nl.aimsites.jenkins.spotitube.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.controller_contract.TrackController;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;

@Default
@RequestScoped
@Named("trackController")
public class TrackControllerImpl implements TrackController {

    private TrackDAO trackDAO;

    @Inject
    public TrackControllerImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public TrackControllerImpl() {
        this(null);
    }

    @Override
    public TrackCollectionDTO addTrackToPlaylist(int playlistId, TrackDTO track) {
        trackDAO.add(playlistId, track);
        return trackDAO.read(playlistId);
    }

    @Override
    public TrackCollectionDTO deleteTrackFromPlaylist(int playlistId, int trackId) {
        trackDAO.remove(playlistId, trackId);
        return trackDAO.read(playlistId);
    }

    @Override
    public TrackCollectionDTO getAllTracksFromPlaylist(int playlistId) {
        return trackDAO.read(playlistId);
    }

    @Override
    public TrackCollectionDTO getAllTracksNotInPlaylist(int playlistId) {
        return trackDAO.readAll(playlistId);
    }

}
