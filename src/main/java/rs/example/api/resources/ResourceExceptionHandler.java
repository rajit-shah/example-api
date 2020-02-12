package rs.example.api.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Generic exception handler class for Exception of type {@link ResourceException}. All
 * exceptions are sent to client as a response with response status 403 with exception message as a
 * {@link Message} bean
 */
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceException.class})
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        ResourceException ire = (ResourceException) e;
        Message message = new Message(ire.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return this.handleExceptionInternal(e, message, headers, HttpStatus.NOT_FOUND, request);
    }

    /**
     * POJO class that can be used to represent an error message
     */
    public static class Message {
        private final String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
