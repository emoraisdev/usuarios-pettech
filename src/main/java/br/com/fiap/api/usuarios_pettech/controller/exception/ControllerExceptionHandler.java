package br.com.fiap.api.usuarios_pettech.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private StandardError error = new StandardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> EntityNotFoundException(ControllerNotFoundException erro, HttpServletRequest request){

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Entity not Found");
        error.setMessage(erro.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException erro, HttpServletRequest request){

        var validateError = new ValidateError();

        validateError.setTimestamp(Instant.now());
        validateError.setStatus(HttpStatus.BAD_REQUEST.value());
        validateError.setError("Erro de validação");
        validateError.setMessage("Houveram erros, verifique os detalhes.");
        validateError.setPath(request.getRequestURI());

        if (!erro.getBindingResult().getFieldErrors().isEmpty()) {
            erro.getBindingResult().getFieldErrors().forEach(f -> {

                validateError.addMensagem(f.getField(), f.getDefaultMessage());
            });
        } else {

            erro.getBindingResult().getAllErrors().forEach(f -> {

                validateError.addMensagem("", f.getDefaultMessage());
            });
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(validateError);
    }
}
