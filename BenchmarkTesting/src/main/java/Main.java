import seed.MongoDBSeed;
import seed.MySQLSeed;

public class Main {
    public static void main(String[] args) {
        MySQLSeed.setup();
        MongoDBSeed.setup();
        Benchmarks benchmarks = new Benchmarks();
        benchmarks.run();
    }
}