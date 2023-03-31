package seed;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLSeed {

    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/MySQLBenchmarkTest";
    private static String user = "root";
    private static String password = "HoihoiHoi123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static void setConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() {
        try {
            PreparedStatement playlistStatement = connection.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS `playlists` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(256) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ");");
            playlistStatement.executeUpdate();

            PreparedStatement trackStatement = connection.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS `tracks` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `title` varchar(256) NOT NULL,\n" +
                    "  `performer` varchar(256) DEFAULT NULL,\n" +
                    "  `duration` int NOT NULL,\n" +
                    "  `album` varchar(256) DEFAULT NULL,\n" +
                    "  `playcount` int DEFAULT NULL,\n" +
                    "  `publicationDate` date DEFAULT NULL,\n" +
                    "  `description` text,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ");");
            trackStatement.executeUpdate();

            PreparedStatement trackPlaylistRelationsStatement = connection.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS `track_playlist_relations` (\n" +
                    "  `playlistId` int NOT NULL,\n" +
                    "  `trackId` int NOT NULL,\n" +
                    "  `offlineAvailable` tinyint(1) NOT NULL DEFAULT '0',\n" +
                    "  KEY `playlistId` (`playlistId`),\n" +
                    "  KEY `trackId` (`trackId`),\n" +
                    "  CONSTRAINT `track_playlist_relations_ibfk_1` FOREIGN KEY (`playlistId`) REFERENCES `playlists` (`id`),\n" +
                    "  CONSTRAINT `track_playlist_relations_ibfk_2` FOREIGN KEY (`trackId`) REFERENCES `tracks` (`id`)\n" +
                    ");");
            trackPlaylistRelationsStatement.executeUpdate();

            System.out.println("Tables created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeAllData() {
        try {
            PreparedStatement trackPlaylistRelationsStatement = connection.prepareStatement("DELETE FROM track_playlist_relations");
            trackPlaylistRelationsStatement.executeUpdate();

            PreparedStatement playlistStatement = connection.prepareStatement("DELETE FROM playlists");
            playlistStatement.executeUpdate();

            PreparedStatement trackStatement = connection.prepareStatement("DELETE FROM tracks");
            trackStatement.executeUpdate();

            System.out.println("All data removed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillPlaylistTable() {
        try {
            InputStream inputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("playlists_mock.json");
            String playlistJson = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(playlistJson);
            String sql = "INSERT INTO playlists (id, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                statement.setInt(1, jsonObject.getInt("id"));
                statement.setString(2, jsonObject.getString("name"));
                statement.executeUpdate();
            }
            System.out.println("Playlists filled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillTrackTable() {
        try {
            InputStream inputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("tracks_mock.json");
            String trackJson = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(trackJson);
            String sql = "INSERT INTO tracks (id, title, performer, duration, album, playcount, publicationDate, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                statement.setInt(1, jsonObject.getInt("id"));
                statement.setString(2, jsonObject.getString("title"));
                statement.setString(3, jsonObject.getString("performer"));
                statement.setInt(4, jsonObject.getInt("duration"));
                statement.setString(5, jsonObject.getString("album"));
                statement.setInt(6, jsonObject.getInt("playcount"));
                statement.setString(7, jsonObject.getString("publicationDate"));
                statement.setString(8, jsonObject.getString("description"));
                statement.executeUpdate();
            }
            System.out.println("Tracks filled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillTrackPlaylistRelationsTable() {
        try {
            InputStream inputStream = MySQLSeed.class.getClassLoader().getResourceAsStream("track_playlist_relations_mock.json");
            String trackPlaylistRelationsJson = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(trackPlaylistRelationsJson);
            String sql = "INSERT INTO track_playlist_relations (playlistId, trackId, offlineAvailable) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                statement.setInt(1, jsonObject.getInt("playlistId"));
                statement.setInt(2, jsonObject.getInt("trackId"));
                statement.setInt(3, jsonObject.getInt("offlineAvailable"));
                statement.executeUpdate();
            }
            System.out.println("TrackPlaylistRelations filled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setup() {
        try {

            setConnection();
            createTables();
            removeAllData();
            fillPlaylistTable();
            fillTrackTable();
            fillTrackPlaylistRelationsTable();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
