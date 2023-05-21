package nl.aimsites.jenkins.spotitube.data.mongodaoimpl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackCollectionDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.dto.TrackDTO;
import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import nl.aimsites.jenkins.spotitube.data.MongoDbConnection;
import nl.aimsites.jenkins.spotitube.data.mappers.DocumentMapper;
import nl.aimsites.jenkins.spotitube.data_access.TrackDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Default
@Named("MongoTrackDAO")
public class TrackDAOImpl implements TrackDAO {
    @Override
    public TrackCollectionDTO read(int playlistId) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            List<Document> tracks = new ArrayList<>();
            tracksCollection.find(new Document("playlists", new Document("$elemMatch", new Document("playlistId", playlistId)))).iterator().forEachRemaining(tracks::add);
            return DocumentMapper.documentListToTrackCollectionDTO(tracks);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public TrackCollectionDTO readAll(int exemptPlaylist) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            List<Document> tracks = new ArrayList<>();
            tracksCollection.find(new Document("playlists", new Document("$not", new Document("$elemMatch", new Document("playlistId", exemptPlaylist))))).iterator().forEachRemaining(tracks::add);
            return DocumentMapper.documentListToTrackCollectionDTO(tracks);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void add(int playlistId, TrackDTO track) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            Document trackFilter = new Document("id", track.getId());
            Document update = new Document("$addToSet", new Document("playlists", new Document("playlistId", Integer.toString(playlistId))));
            tracksCollection.updateOne(trackFilter, update);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void remove(int playlistId, int trackId) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> trackCollection = database.getCollection("tracks");
            Document trackFilter = new Document("id", trackId);
            trackCollection.updateOne(trackFilter, new Document("$pull", new Document("playlists", playlistId)));
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void removeAll(int playlistId) throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            Document filter = new Document("playlists", new Document("$elemMatch", new Document("playlistId", playlistId)));
            Document update = new Document("$pull", new Document("playlists", new Document("playlistId", playlistId)));
            tracksCollection.updateMany(filter, update);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public int calculateTrackLengthInSeconds() throws DataAccessException {
        try {
            MongoDatabase database = MongoDbConnection.getDatabase();
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            List<Document> tracks = new ArrayList<>();
            tracksCollection.find().iterator().forEachRemaining(tracks::add);
            int totalDuration = 0;
            for (Document track : tracks) {
                totalDuration += Integer.parseInt(track.getString("duration"));
            }
            return totalDuration;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
