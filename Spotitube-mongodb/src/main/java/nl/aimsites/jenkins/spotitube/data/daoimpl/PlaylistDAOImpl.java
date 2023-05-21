package nl.aimsites.jenkins.spotitube.data.daoimpl;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.data.mappers.ResultSetMapper;
import nl.aimsites.jenkins.spotitube.data_access.PlaylistDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Vetoed
@Named("MySQLPlaylistDAO")
public class PlaylistDAOImpl implements PlaylistDAO {
    @Override
    public int create(String name) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO playlists (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, name);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            PreparedStatement playlistTableStatement = connection.prepareStatement("DELETE FROM playlists WHERE id = ?;");
            playlistTableStatement.setInt(1, id);
            playlistTableStatement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(String name, int id) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("UPDATE playlists SET name = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public PlaylistCollectionDTO read() throws DataAccessException {
        try {
            try(ResultSet resultSet = executePlaylistSelectQuery(0)) {
                return ResultSetMapper.mapToPlaylistDTOCollection(resultSet);
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public PlaylistDTO read(int id) throws DataAccessException {
        try {
            try(ResultSet resultSet = executePlaylistSelectQuery(id)) {
                return ResultSetMapper.mapToPlaylistDTO(resultSet);
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Boolean isOwner(int id, String user) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM user_playlist_relations WHERE user = ? AND playlistId = ?");
            statement.setString(1, user);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Executes a query to select all playlists from the database
     * @return The ResultSet of the query
     * @throws DataAccessException Read or write error
     */
    public ResultSet executePlaylistSelectQuery(int id) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement;
            if (id != 0) {
                statement = connection.prepareStatement("SELECT * FROM playlists WHERE id = ?");
                statement.setInt(1, id);
            } else {
                statement = connection.prepareStatement("SELECT * FROM playlists");
            }
            return statement.executeQuery();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
