package seed;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MongoDBSeed {

    static String connectionString = "mongodb://localhost:27017";
    static MongoClient mongoClient;
    static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(connectionString);
        return mongoClient.getDatabase("mongodb-benchmarktest");
    }

    private static void setConnection() {
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase("mongodb-benchmarktest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCollections() {
        MongoCollection<Document> playlistsCollection = database.getCollection("playlists");
        MongoCollection<Document> tracksCollection = database.getCollection("tracks");
        if (playlistsCollection == null) {
            database.createCollection("playlists");
        }
        if (tracksCollection == null) {
            database.createCollection("tracks");
        }
        System.out.println("Collections created");
    }

    private static void removeAllDocuments() {
        MongoCollection<Document> playlistsCollection = database.getCollection("playlists");
        playlistsCollection.deleteMany(new Document());
        MongoCollection<Document> tracksCollection = database.getCollection("tracks");
        tracksCollection.deleteMany(new Document());
        System.out.println("All documents removed");
    }

    private static void fillPlaylistCollection() {
        try {
            InputStream inputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("playlists_mock.json");
            String playlistJson = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray playlistsArray = new JSONArray(playlistJson);
            MongoCollection<Document> playlistsCollection = database.getCollection("playlists");
            for (Object obj : playlistsArray) {
                if (obj instanceof JSONObject) {
                    Document playlist = Document.parse(obj.toString());
                    playlistsCollection.insertOne(playlist);
                }
            }
            System.out.println("Playlists inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillTrackCollection() {
        try {
            InputStream inputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("tracks_mock.json");
            String trackJson = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray tracksArray = new JSONArray(trackJson);
            MongoCollection<Document> tracksCollection = database.getCollection("tracks");
            for (Object obj : tracksArray) {
                if (obj instanceof JSONObject) {
                    Document track = Document.parse(obj.toString());
                    tracksCollection.insertOne(track);
                }
            }
            System.out.println("Tracks inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPlaylistRelations() {
        try {
            // Load relations from JSON file
            InputStream relationsInputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("track_playlist_relations_mock.json");
            String relationsJson = new String(relationsInputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray relationsArray = new JSONArray(relationsJson);

            MongoCollection<Document> tracksCollection = database.getCollection("tracks");

            for (Object obj : relationsArray) {
                if (obj instanceof JSONObject) {
                    JSONObject relation = (JSONObject) obj;
                    String trackId = relation.getString("trackId");
                    Document trackFilter = new Document("id", trackId);
                    Document track = tracksCollection.find(trackFilter).first();
                    if (track != null) {
                        List<Document> playlists = (List<Document>) track.get("playlists");
                        if (playlists == null) {
                            playlists = new ArrayList<Document>();
                        }
                        String playlistId = relation.getString("playlistId");
                        String offlineAvailable = relation.getString("offlineAvailable");
                        Document playlist = new Document("playlistId", playlistId)
                                .append("offlineAvailable", offlineAvailable);
                        playlists.add(playlist);
                        tracksCollection.updateOne(trackFilter, new Document("$set", new Document("playlists", playlists)));
                    }
                }
            }

            System.out.println("Relations added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setup() {
        setConnection();
        removeAllDocuments();
        createCollections();
        fillPlaylistCollection();
        fillTrackCollection();
        addPlaylistRelations();
    }

}
