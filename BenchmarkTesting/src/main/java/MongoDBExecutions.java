import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBExecutions {

    public static long getAllPlaylists(MongoDatabase database) {
        try {
            long startTime;
            long endTime;
            MongoCollection<Document> playlistsCollection = database.getCollection("playlists");
            startTime = System.currentTimeMillis();
            playlistsCollection.find().iterator().forEachRemaining(document -> {
            });
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getAllTracks(MongoDatabase database) {
        try {
            long startTime;
            long endTime;
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            startTime = System.currentTimeMillis();
            tracksCollection.find().iterator().forEachRemaining(document -> {
            });
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getTracksInPlaylistById(MongoDatabase database, int id) {
        try {
            long startTime;
            long endTime;
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            startTime = System.currentTimeMillis();
            tracksCollection.find(new Document("playlists", new Document("$elemMatch", new Document("playlistId", "1")))).iterator().forEachRemaining(document -> {
            });
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long calculateTrackLength(MongoDatabase database) {
        try {
            long startTime;
            long endTime;
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            startTime = System.currentTimeMillis();
            tracksCollection.find().iterator().forEachRemaining(document -> {
                document.getString("duration");
            });
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
