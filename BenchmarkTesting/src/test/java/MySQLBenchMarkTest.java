import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;

import seed.MySQLSeed;

public class MySQLBenchMarkTest {

    private static Connection connection;

    @BeforeAll
    public static void setup() {
        try {

            connection = MySQLSeed.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
