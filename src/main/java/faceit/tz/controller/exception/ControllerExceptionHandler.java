package faceit.tz.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestams", new Date());

        HttpStatus status = HttpStatus.NOT_FOUND;

        body.put("status", status.value());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestams", new Date());

        HttpStatus status = HttpStatus.BAD_REQUEST;

        body.put("status", status.value());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestams", new Date());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(x-> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors",errors);

        return new ResponseEntity<>(body, status);
    }
}

