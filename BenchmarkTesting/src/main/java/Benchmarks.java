import com.mongodb.client.MongoDatabase;
import seed.MongoDBSeed;
import seed.MySQLSeed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Benchmarks {

    private static Connection connection;
    private static MongoDatabase database;
    private static final int ITERATIONS = 1000;

    public Benchmarks() {

    }

    public void run() {
        createConnections();
        long[] getAllPlaylistsDurations = testGetAllPlaylists();
        long[] getAllTracksDurations = testGetAllTracks();
        long[] getTracksInPlaylistByIdDurations = getTracksInPlaylistById();
        long[] getCalculateTrackLengthDurations = calculateTrackLength();

        long[][] durations = new long[][]{
                getAllPlaylistsDurations,
                getAllTracksDurations,
                getTracksInPlaylistByIdDurations,
                getCalculateTrackLengthDurations
        };

        long[] totalDurations = getTotalDuration(durations);
        float averageMongoDuration = (float)totalDurations[0] / 4;
        float averageMySQLDuration = (float)totalDurations[1] / 4;

        if (averageMongoDuration < averageMySQLDuration) {
            float percentage = 100 - ((averageMongoDuration / averageMySQLDuration) * 100);
            percentage = Math.round(percentage * 10) / 10f;
            System.out.println("MongoDB is on average " + percentage + "% faster than MySQL");
        } else {
            float percentage = 100 - ((averageMySQLDuration / averageMongoDuration) * 100);
            percentage = Math.round(percentage * 10) / 10f;
            System.out.println("MySQL is on average " + percentage + "% faster than MongoDB");
        }
    }

    public void createConnections() {
        try {
            connection = MySQLSeed.getConnection();
            database = MongoDBSeed.getDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long[] testGetAllPlaylists() {
        long[] mysqlDurations = new long[ITERATIONS];
        long[] mongoDurations = new long[ITERATIONS];
        for (int i = 0; i < mysqlDurations.length; i++) {
            mysqlDurations[i] = MySQLExecutions.getAllPlaylists(connection);
        }
        for (int i = 0; i < mongoDurations.length; i++) {
            mongoDurations[i] = MongoDBExecutions.getAllPlaylists(database);
        }
        long mysqlDuration = calculateAverage(mysqlDurations);
        long mongoDuration = calculateAverage(mongoDurations);
        long mysqlTotal = calculateTotal(mysqlDurations);
        long mongoTotal = calculateTotal(mongoDurations);

        printResults("getAllPlaylists", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal);

        return getDurationArray(mongoTotal, mysqlTotal);
    }

    public long[] testGetAllTracks() {
        long[] mysqlDurations = new long[ITERATIONS];
        long[] mongoDurations = new long[ITERATIONS];
        for (int i = 0; i < mysqlDurations.length; i++) {
            mysqlDurations[i] = MySQLExecutions.getAllTracks(connection);
        }
        for (int i = 0; i < mongoDurations.length; i++) {
            mongoDurations[i] = MongoDBExecutions.getAllTracks(database);
        }
        long mysqlDuration = calculateAverage(mysqlDurations);
        long mongoDuration = calculateAverage(mongoDurations);
        long mysqlTotal = calculateTotal(mysqlDurations);
        long mongoTotal = calculateTotal(mongoDurations);

        printResults("getAllTracks", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal);

        return getDurationArray(mongoTotal, mysqlTotal);
    }

    public long[] getTracksInPlaylistById() {
        long[] mysqlDurations = new long[ITERATIONS];
        long[] mongoDurations = new long[ITERATIONS];
        for (int i = 0; i < mysqlDurations.length; i++) {
            mysqlDurations[i] = MySQLExecutions.getTracksInPlaylistById(connection, 1);
        }
        for (int i = 0; i < mongoDurations.length; i++) {
            mongoDurations[i] = MongoDBExecutions.getTracksInPlaylistById(database, 1);
        }
        long mysqlDuration = calculateAverage(mysqlDurations);
        long mongoDuration = calculateAverage(mongoDurations);
        long mysqlTotal = calculateTotal(mysqlDurations);
        long mongoTotal = calculateTotal(mongoDurations);

        printResults("getTracksInPlaylistById", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal);

        return getDurationArray(mongoTotal, mysqlTotal);
    }

    public long[] calculateTrackLength() {
        long[] mysqlDurations = new long[ITERATIONS];
        long[] mongoDurations = new long[ITERATIONS];
        for (int i = 0; i < mysqlDurations.length; i++) {
            mysqlDurations[i] = MySQLExecutions.calculateTrackLength(connection);
        }
        for (int i = 0; i < mongoDurations.length; i++) {
            mongoDurations[i] = MongoDBExecutions.calculateTrackLength(database);
        }
        long mysqlDuration = calculateAverage(mysqlDurations);
        long mongoDuration = calculateAverage(mongoDurations);
        long mysqlTotal = calculateTotal(mysqlDurations);
        long mongoTotal = calculateTotal(mongoDurations);

        printResults("calculateTrackLengthInSeconds", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal);

        return getDurationArray(mongoTotal, mysqlTotal);
    }

    private long calculateAverage(long[] durations) {
        long durationAverage = 0;
        for (int i = 0; i < durations.length; i++) {
            durationAverage += durations[i];
        }
        durationAverage /= durations.length;
        return durationAverage;
    }

    private long calculateTotal(long[] durations) {
        long durationTotal = 0;
        for (int i = 0; i < durations.length; i++) {
            durationTotal += durations[i];
        }
        return durationTotal;
    }

    private long[] getDurationArray(long mongoDbDuration, long mySqlDuration) {
        long[] durations = new long[2];
        durations[0] = mongoDbDuration;
        durations[1] = mySqlDuration;
        return durations;
    }

    private long[] getTotalDuration(long[][] durations) {
        long mongoDbTotalDuration = 0;
        long mySqlTotalDuration = 0;
        for (int i = 0; i < durations.length; i++) {
            mongoDbTotalDuration += durations[i][0];
            mySqlTotalDuration += durations[i][1];
        }
        long[] totalDurations = new long[2];
        totalDurations[0] = mongoDbTotalDuration;
        totalDurations[1] = mySqlTotalDuration;
        return totalDurations;
    }

    private void printResults(String text, long mongoDbAvgResults, long mySqlAvgResults, long mongoDbTotalResults, long mySqlTotalResults) {
        System.out.println("Testing " + text);
        System.out.println("MySQL average duration: " + mongoDbAvgResults + "ms");
        System.out.println("MongoDB average duration: " + mySqlAvgResults + "ms");
        System.out.println("\n");
        System.out.println("MySQL total duration: " + mongoDbTotalResults + "ms");
        System.out.println("MongoDB total duration: " + mySqlTotalResults + "ms");
        System.out.println("---------------------------------------------------------");
    }

}
