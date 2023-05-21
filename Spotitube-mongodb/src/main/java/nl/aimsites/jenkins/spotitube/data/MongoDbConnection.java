package nl.aimsites.jenkins.spotitube.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnection {

    static String connectionString = "mongodb://localhost:27017";

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(connectionString);
        return mongoClient.getDatabase("mongodb-benchmarktest");
    }

}
