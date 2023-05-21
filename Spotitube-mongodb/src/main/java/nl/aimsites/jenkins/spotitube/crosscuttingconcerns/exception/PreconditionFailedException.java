package nl.aimsites.jenkins.spotitube.crosscuttingconcerns.exception;

import jakarta.ws.rs.WebApplicationException;

public class PreconditionFailedException extends RuntimeException {

        public PreconditionFailedException(String message) {
            super(message);
        }

        public PreconditionFailedException(String message, Throwable cause) {
            super(message, cause);
        }

}
