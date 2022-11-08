package toy.blog.be.controller.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        var msg = exception.getMessage();

        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse<>(HttpStatus.NOT_FOUND, msg));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArguments(IllegalArgumentException exception) {
        var msg = exception.getMessage();

        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse<>(HttpStatus.BAD_REQUEST, msg));
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<Object> handleIllegalArguments(HttpRequestMethodNotSupportedException exception, WebRequest webRequest) {
        var msg = exception.getMessage();
        var params = webRequest.getParameterMap().entrySet();

        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ExceptionResponse<>(HttpStatus.METHOD_NOT_ALLOWED, msg));
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleServerExceptions(Exception exception, WebRequest webRequest) {
        var msg = exception.getMessage();

        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, msg));
    }


    @Getter
    private static class ExceptionResponse<T> {
        private final T status;
        private final T msg;

        ExceptionResponse(T status, T msg) {
            this.status = status;
            this.msg = msg;
        }
    }

}
