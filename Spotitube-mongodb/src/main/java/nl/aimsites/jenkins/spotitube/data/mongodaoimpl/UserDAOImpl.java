package nl.aimsites.jenkins.spotitube.data.mongodaoimpl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.LoginDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.MongoDbConnection;
import nl.aimsites.jenkins.spotitube.data.mappers.DocumentMapper;
import nl.aimsites.jenkins.spotitube.data_access.UserDAO;
import org.bson.Document;

@Default
@Named("MongoUserDAO")
public class UserDAOImpl implements UserDAO {
    @Override
    public String alterToken(String user, String token) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> usersCollection = database.getCollection("users");
            Document userFilter = new Document("user", user);
            usersCollection.updateOne(userFilter, new Document("$set", new Document("token", token)));
            return token;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public String readByToken(String token) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> usersCollection = database.getCollection("users");
            Document user = usersCollection.find(new Document("token", token)).first();
            if (user != null) {
                return user.getString("user");
            }
            return null;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public LoginDTO readByUser(String user) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> usersCollection = database.getCollection("users");
            Document userDocument = usersCollection.find(new Document("user", user)).first();
            if (userDocument != null) {
                return DocumentMapper.documentToLoginDTO(userDocument);
            }
            return null;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void addPlaylistOwnership(String user, int playlistId) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> usersCollection = database.getCollection("users");
            Document userFilter = new Document("user", user);
            usersCollection.updateOne(userFilter, new Document("$addToSet", new Document("playlists", playlistId)));
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void deletePlaylistOwnership(String user, int playlistId) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> usersCollection = database.getCollection("users");
            Document userFilter = new Document("user", user);
            usersCollection.updateOne(userFilter, new Document("$pull", new Document("playlists", playlistId)));
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
