package engine.exception;

public class EmailAlreadyTakenException extends BadHttpRequestException {
    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
