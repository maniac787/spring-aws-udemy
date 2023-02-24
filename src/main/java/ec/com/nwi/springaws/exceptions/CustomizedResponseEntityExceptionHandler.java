package ec.com.nwi.springaws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).message(
                ex.getMessage()).details(request.getDescription(false)).build();

        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).message(
                ex.getMessage()).details(request.getDescription(false)).build();

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<p>");
        stringBuilder.append("Total Errors: " + ex.getErrorCount());
        stringBuilder.append(" ");


        for (FieldError fieldError : ex.getFieldErrors()) {
            stringBuilder.append("Error on [" + fieldError.getField() + "] :: " + fieldError.getDefaultMessage());
            stringBuilder.append("; ");
        }

        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(stringBuilder.toString())
                .details(request.getDescription(false))
                .build();

        stringBuilder.append("</p>");

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
