package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception;

import jakarta.ws.rs.WebApplicationException;

public class DataAccessException extends RuntimeException {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}