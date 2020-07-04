package com.bri.restservice.controllers;

import com.bri.restservice.exceptions.ApplicationException;
import com.bri.restservice.exceptions.ErrorCodes;
import com.bri.restservice.models.ResponseMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseMessage> handleApplicationException(ApplicationException e) {
        //System.out.println("#" + LocaleContextHolder.getLocale());
        String message = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
        ResponseMessage body = ResponseMessage.error(e.getCode(), message);
        return ResponseEntity.ok(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        
        e.getBindingResult().getFieldErrors().forEach((fieldError)->{
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            
           List<String> messages = errors.get(field);
           if(messages == null){
               messages = new ArrayList();
               
               errors.put(field, messages);
           }
            messages.add(message);
            
            
        });
        
        String message = messageSource.getMessage("entity.notvalid", null, LocaleContextHolder.getLocale());
//        String message = "Entity not valid";
        ResponseMessage body = ResponseMessage.error(ErrorCodes.ENTITY_NOT_VALID, message, errors);
        return ResponseEntity.ok(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleUnknownException(Exception e) {
        ResponseMessage body = ResponseMessage.error(ErrorCodes.UNKNOWN, e.getMessage());
        return ResponseEntity.ok(body);
    }

}
