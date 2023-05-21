import nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception.DataAccessException;
import org.junit.Test;

public class ApplicationTests {

    @Test(expected = DataAccessException.class)
    public void testConstructorWithMessage() {
        String message = "Test message";
        throw new DataAccessException(message);
    }

    @Test(expected = DataAccessException.class)
    public void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Exception("Test cause");
        throw new DataAccessException(message, cause);
    }
}