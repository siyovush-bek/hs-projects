package recipes.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RecipeNotFoundException.class})
    ResponseEntity<Object> handleResponseNotFoundException(RecipeNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions (MethodArgumentNotValidException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleInvalidParamExceptions (BadRequestException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleDBExceptions(Exception e) {
        return ResponseEntity.badRequest().build();
    }
}
