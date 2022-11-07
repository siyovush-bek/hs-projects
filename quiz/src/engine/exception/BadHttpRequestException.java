package engine.exception;

public class BadHttpRequestException extends RuntimeException{
    public BadHttpRequestException(String message) {
        super(message);
    }
}
