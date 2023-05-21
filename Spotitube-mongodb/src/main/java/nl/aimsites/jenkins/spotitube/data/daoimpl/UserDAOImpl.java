package nl.aimsites.jenkins.spotitube.data.daoimpl;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.data.mappers.ResultSetMapper;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Vetoed
@Named("MySQLUserDAO")
public class UserDAOImpl implements UserDAO {

    @Override
    public String alterToken(String user, String token) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "UPDATE users SET token = ? WHERE user = ?"
            );
            statement.setString(1, token);
            statement.setString(2, user);
            statement.executeUpdate();
            return token;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public String readByToken(String token) throws DataAccessException {
        try {
            try(ResultSet resultSet = executeUserSelectQuery("token", token)) {
                if (resultSet.next()) {
                    return resultSet.getString("user");
                }
                return null;
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public LoginDTO readByUser(String user) throws DataAccessException {
        try {
            try(ResultSet resultSet = executeUserSelectQuery("user", user)) {
                return ResultSetMapper.mapToLoginDTO(resultSet);
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void addPlaylistOwnership(String user, int playlistId) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT IGNORE INTO user_playlist_relations (user, playlistId) VALUES (?, ?)"
            );
            statement.setString(1, user);
            statement.setInt(2, playlistId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void deletePlaylistOwnership(String user, int playlistId) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM user_playlist_relations WHERE user = ? AND playlistId = ?"
            );
            statement.setString(1, user);
            statement.setInt(2, playlistId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private ResultSet executeUserSelectQuery(String field, String value) throws DataAccessException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE " + field + " = ?");
            statement.setString(1, value);
            return statement.executeQuery();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
