package rs.example.api.resources;

/**
 * Generic exception to be thrown during problem in fulfilling the service request
 */
public class ResourceException extends RuntimeException {
    public ResourceException(String message) {
        super(message);
    }
}
