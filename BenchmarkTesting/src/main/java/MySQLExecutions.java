import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLExecutions {

    public static long getAllPlaylists(Connection connection) {
        try {
            long startTime;
            long endTime;

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlists");
            startTime = System.currentTimeMillis();
            statement.executeQuery();
            endTime = System.currentTimeMillis();

            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getAllTracks(Connection connection) {
        try {
            long startTime;
            long endTime;

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tracks");
            startTime = System.currentTimeMillis();
            statement.executeQuery();
            endTime = System.currentTimeMillis();

            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getTracksInPlaylistById(Connection connection, int id) {
        try {
            long startTime;
            long endTime;

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tracks T LEFT OUTER JOIN track_playlist_relations R ON R.trackId=T.id WHERE R.playlistId = ?");
            statement.setInt(1, id);
            startTime = System.currentTimeMillis();
            statement.executeQuery();
            endTime = System.currentTimeMillis();

            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long calculateTrackLength(Connection connection) {
        try {
            long startTime;
            long endTime;

            PreparedStatement statement = connection.prepareStatement("SELECT duration FROM tracks T INNER JOIN track_playlist_relations R ON R.trackId=T.id");
            startTime = System.currentTimeMillis();
            try(ResultSet resultSet = statement.executeQuery()) {
                int totalLength = 0;
                while (resultSet.next()) {
                    totalLength += resultSet.getInt("duration");
                }
            }
            endTime = System.currentTimeMillis();

            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
