import seed.MongoDBSeed;
import seed.MySQLSeed;

public class Main {
    /**
     * The main method to run the benchmarks.
     * First, the database is seeded with data. Second, the benchmarks are run.
     * This can take a while! Be patient. The results will be printed to the console.
     */
    public static void main(String[] args) {
        MySQLSeed.setup();
        MongoDBSeed.setup();
        Benchmarks benchmarks = new Benchmarks();
        benchmarks.run();
    }
}