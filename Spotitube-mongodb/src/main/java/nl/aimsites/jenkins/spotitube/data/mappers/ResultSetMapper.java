package nl.aimsites.jenkins.spotitube.data.mappers;

import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.*;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper {

    /**
     * Generates a {@link PlaylistCollectionDTO} from a {@link ResultSet}
     * @param resultSet The ResultSet to generate the PlaylistCollectionDTO from
     * @return The generated PlaylistCollectionDTO
     * @throws DataAccessException Read or write error
     */
    public static PlaylistCollectionDTO mapToPlaylistDTOCollection(ResultSet resultSet) throws DataAccessException {
        try {
            List<PlaylistDTO> playlists = new ArrayList<>();

            while (resultSet.next()) {
                playlists.add(new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), false, null));
            }
            if (playlists.isEmpty()) return null;

            PlaylistDTO[] playlistArray = new PlaylistDTO[playlists.size()];
            playlistArray = playlists.toArray(playlistArray);

            PlaylistCollectionDTO playlistCollectionDTO = new PlaylistCollectionDTO(playlistArray, 0);
            return playlistCollectionDTO;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Generates a {@link TrackCollectionDTO} from a {@link ResultSet}
     * @param resultSet The ResultSet to generate the TrackCollectionDTO from
     * @return The generated TrackCollectionDTO
     * @throws DataAccessException Read or write error
     */
    public static TrackCollectionDTO mapToTrackDTOCollection(ResultSet resultSet) throws DataAccessException {
        try {
            List<TrackDTO> tracks = new ArrayList<>();

            while (resultSet.next()) {
                tracks.add(new TrackDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("performer"),
                        resultSet.getInt("duration"),
                        resultSet.getString("album"),
                        resultSet.getInt("playcount"),
                        resultSet.getString("publicationDate"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("offlineAvailable")
                ));
            }
            if (tracks.isEmpty()) return null;

            TrackDTO[] trackArray = new TrackDTO[tracks.size()];
            trackArray = tracks.toArray(trackArray);

            TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO(trackArray);
            return trackCollectionDTO;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Generates a {@link PlaylistDTO} from a {@link ResultSet}
     * @param resultSet The ResultSet to generate the PlaylistDTO from
     * @return The generated PlaylistDTO
     * @throws DataAccessException Read or write error
     */
    public static PlaylistDTO mapToPlaylistDTO(ResultSet resultSet) throws DataAccessException {
        try {
            PlaylistDTO playlistDTO = null;
            while (resultSet.next()) {
                playlistDTO = new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), false, null);
            }
            return playlistDTO;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Generates a {@link LoginDTO} from a {@link ResultSet}
     * @param resultSet The ResultSet to generate the LoginDTO from
     * @return The generated LoginDTO
     * @throws DataAccessException Read or write error
     */
    public static LoginDTO mapToLoginDTO(ResultSet resultSet) throws DataAccessException {
        try {
            if (resultSet.next()) {
                return new LoginDTO(resultSet.getString("token"), resultSet.getString("user"), resultSet.getString("password"));
            }
            return null;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
