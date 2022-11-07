package engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {QuizNotFoundException.class})
    ResponseEntity<Object> handleQuizNotFoundException(QuizNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions (MethodArgumentNotValidException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {EmailAlreadyTakenException.class})
    public ResponseEntity<Object> handleException(BadHttpRequestException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Object> handleException(ForbiddenException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
