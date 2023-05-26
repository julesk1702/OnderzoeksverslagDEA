import com.mongodb.client.MongoDatabase;
import seed.MongoDBSeed;
import seed.MySQLSeed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Benchmarks {

    private static Connection connection;
    private static MongoDatabase database;
    private static final int ITERATIONS = 10000;

    public Benchmarks() {

    }

    public void run() {
        createConnections();
        testGetAllPlaylists();
        testGetAllTracks();
        getTracksInPlaylistById();
        calculateTrackLength();
    }

    public void createConnections() {
        try {
            connection = MySQLSeed.getConnection();
            database = MongoDBSeed.getDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetAllPlaylists() {
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
        long percentage = calculatePercentage(mongoTotal, mysqlTotal);

        printResults("getAllPlaylists", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal, percentage);
    }

    public void testGetAllTracks() {
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
        long percentage = calculatePercentage(mongoTotal, mysqlTotal);

        printResults("getAllTracks", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal, percentage);
    }

    public void getTracksInPlaylistById() {
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
        long percentage = calculatePercentage(mongoTotal, mysqlTotal);

        printResults("getTracksInPlaylistById", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal, percentage);
    }

    public void calculateTrackLength() {
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
        long percentage = calculatePercentage(mongoTotal, mysqlTotal);

        printResults("calculateTrackLengthInSeconds", mongoDuration, mysqlDuration, mongoTotal, mysqlTotal, percentage);
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

    private long calculatePercentage(long mongo, long mysql) {
        if ((double) mongo < mysql) {
            return (long) ((double) mongo / mysql * 100);
        } else {
            return (long) ((double) mysql / mongo * 100);
        }
    }

    private void printResults(String text, long mongoDbAvgResults, long mySqlAvgResults, long mongoDbTotalResults, long mySqlTotalResults, long percentage) {
        System.out.println("Testing " + text);
        System.out.println("MySQL average duration: " + mongoDbAvgResults + "ms");
        System.out.println("MongoDB average duration: " + mySqlAvgResults + "ms");
        System.out.println("\n");
        System.out.println("MySQL total duration: " + mongoDbTotalResults + "ms");
        System.out.println("MongoDB total duration: " + mySqlTotalResults + "ms");
        System.out.println("\n");
        System.out.println(percentage + " faster");
        System.out.println("---------------------------------------------------------");
    }

}
