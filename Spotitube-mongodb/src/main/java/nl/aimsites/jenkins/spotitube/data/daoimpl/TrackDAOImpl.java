package nl.aimsites.jenkins.spotitube.data.daoimpl;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.data.mappers.ResultSetMapper;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Vetoed
@Named("MySQLTrackDAO")
public class TrackDAOImpl implements TrackDAO {
    @Override
    public TrackCollectionDTO read(int playlistId) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "SELECT * FROM tracks T LEFT OUTER JOIN track_playlist_relations R ON R.trackId=T.id WHERE R.playlistId = ?"
            );
            statement.setInt(1, playlistId);
            try(ResultSet resultSet = statement.executeQuery()) {
                return ResultSetMapper.mapToTrackDTOCollection(resultSet);
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public TrackCollectionDTO readAll(int exemptPlaylist) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT T.*, R.offlineAvailable FROM tracks T LEFT OUTER JOIN track_playlist_relations R ON R.trackId=T.Id WHERE id NOT IN (SELECT trackId FROM track_playlist_relations WHERE playlistId = ?) AND (R.offlineAvailable = 0 OR R.offlineAvailable IS NULL);"
            );
            statement.setInt(1, exemptPlaylist);
            try(ResultSet resultSet = statement.executeQuery()) {
                return ResultSetMapper.mapToTrackDTOCollection(resultSet);
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void add(int playlistId, TrackDTO track) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO track_playlist_relations VALUES (?, ?, ?)"
            );
            statement.setInt(1, playlistId);
            statement.setInt(2, track.getId());
            statement.setInt(3, track.isOfflineAvailable() ? 1 : 0);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void remove(int playlistId, int trackId) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "DELETE FROM track_playlist_relations WHERE playlistId = ? AND trackId = ?"
            );
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void removeAll(int playlistId) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "DELETE FROM track_playlist_relations WHERE playlistId = ?"
            );
            statement.setInt(1, playlistId);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public int calculateTrackLengthInSeconds() throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "SELECT duration FROM tracks T INNER JOIN track_playlist_relations R ON R.trackId=T.id"
            );
            try(ResultSet resultSet = statement.executeQuery()) {
                int totalLength = 0;
                while (resultSet.next()) {
                    totalLength += resultSet.getInt("duration");
                }
                return totalLength;
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
