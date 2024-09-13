package com.bank.client.application.exception;

import com.bank.client.application.dto.response.ResponseFormatDTO;
import com.bank.client.domain.exception.DomainException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    protected ResponseEntity<ResponseFormatDTO<Map<String, Object>>> handleValidatorException(Exception ex) {
        ResponseFormatDTO<Map<String, Object>> response = new ResponseFormatDTO<>();
        String errorFieldsMessage = "Campos faltantes o incorrectos";
        response.setMessage(errorFieldsMessage);
        response.setStatusCode(400);
        response.setError(true);

        Map<String, Object> errors = new HashMap<>();

        if (ex instanceof MethodArgumentTypeMismatchException exc){
            errors.put(exc.getPropertyName(), String.format("Valor [%s] no permitido", exc.getValue()));
        } else if(ex instanceof HttpMessageNotReadableException exc) {
            InvalidFormatException invExc = (InvalidFormatException)exc.getCause();
            errors.put(invExc.getPath().get(0).getFieldName(), String.format("Valor [%s] no permitido", invExc.getValue()));
        }else if (ex instanceof MissingServletRequestParameterException exc) {
            errors.put(exc.getParameterName(), "Campo obligatorio");
        }else if(ex instanceof MethodArgumentNotValidException exc){
            for (FieldError fieldError: exc.getBindingResult()
                    .getFieldErrors()){
                if(fieldError.isBindingFailure()){
                    errors.put(fieldError.getField(), "Valor no permitido");
                }else{

                    ConstraintViolation c = fieldError.unwrap(ConstraintViolation.class);

                    Iterator<Path.Node> propertyIterator = c.getPropertyPath().iterator();
                    String parentProperty = "";
                    int i = 1;

                    while(propertyIterator.hasNext()){
                        Path.Node node = propertyIterator.next();
                        if(node.getKind().name().equals("PROPERTY") && propertyIterator.hasNext()){
                            if(errors.get(node.toString()) == null){
                                errors.put(node.toString(),new HashMap<String, String>());
                            }

                            parentProperty = node.toString();
                        }else if(i > 1){
                            Map<String,String> property = (Map<String, String>) errors.get(parentProperty);
                            property.put(node.getName(), c.getMessage());

                        }else{
                            errors.put(node.getName(), c.getMessage());
                        }
                        i++;
                    }
                }

            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("errors",errors);

        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ResponseFormatDTO<String>> handleMediaTypeException(HttpMediaTypeNotSupportedException exc){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();
        response.setMessage(exc.getMessage());
        response.setError(true);
        response.setStatusCode(415);
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ResponseFormatDTO<String>> handleVersionException(DomainException ex){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();
        response.setMessage(ex.getMessage());
        response.setError(true);
        response.setStatusCode(ex.getStatusCode());

        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatusCode()));
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseFormatDTO<String>> handleException(Exception ex){
        ResponseFormatDTO<String> response = new ResponseFormatDTO<>();
        response.setMessage("Error");
        response.setStatusCode(500);
        response.setError(true);
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
