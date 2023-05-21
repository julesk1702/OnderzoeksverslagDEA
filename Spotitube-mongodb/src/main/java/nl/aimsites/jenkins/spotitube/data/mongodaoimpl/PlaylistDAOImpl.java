package nl.aimsites.jenkins.spotitube.data.mongodaoimpl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.PlaylistDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.MongoDbConnection;
import nl.aimsites.jenkins.spotitube.data.mappers.DocumentMapper;
import nl.aimsites.jenkins.spotitube.data_access.PlaylistDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Default
@Named("MongoPlaylistDAO")
public class PlaylistDAOImpl implements PlaylistDAO {
    @Override
    public int create(String name) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> playlistCollection = database.getCollection("playlists");
            String id = Long.toString(playlistCollection.countDocuments() + 1);
            Document playlist = new Document("id", id)
                    .append("name", name);
            playlistCollection.insertOne(playlist);
            return Integer.parseInt(id);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> playlistCollection = database.getCollection("playlists");
            String idString = Integer.toString(id);
            Document playlistFilter = new Document("id", idString);
            playlistCollection.deleteOne(playlistFilter);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(String name, int id) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> playlistCollection = database.getCollection("playlists");
            String idString = Integer.toString(id);
            Document playlistFilter = new Document("id", idString);
            playlistCollection.updateOne(playlistFilter, new Document("$set", new Document("name", name)));
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public PlaylistCollectionDTO read() throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> playlistCollection = database.getCollection("playlists");
            List<Document> playlists = new ArrayList<>();
            playlistCollection.find(new Document()).iterator().forEachRemaining(playlists::add);
            return DocumentMapper.documentListToPlaylistCollectionDTO(playlists);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public PlaylistDTO read(int id) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> playlistCollection = database.getCollection("playlists");
            Document playlist = playlistCollection.find(new Document("id", Integer.toString(id))).first();
            if (playlist != null) {
                return DocumentMapper.documentToPlaylistDTO(playlist);
            }
            return null;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Boolean isOwner(int id, String user) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> userCollection = database.getCollection("users");
            Document userFilter = new Document("user", user);
            Document userDocument = userCollection.find(userFilter).first();
            if (userDocument != null) {
                List<Integer> playlists = userDocument.getList("playlists", Integer.class);
                if (playlists == null) return false;
                for (int playlist : playlists) {
                    if (playlist == id) return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

}
