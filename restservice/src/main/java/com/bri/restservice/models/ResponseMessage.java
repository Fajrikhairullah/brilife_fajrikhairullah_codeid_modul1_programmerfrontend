package com.bri.restservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ResponseMessage<T> {
    
    private int code;
    private String message;
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime localDateTime;

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.localDateTime = LocalDateTime.now();
    }

    public ResponseMessage(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    
    public static <T> ResponseMessage<T> success(T data){
        return new ResponseMessage(0, null, data);
    }
    public static <T> ResponseMessage<T> error(int code, String message){
        return error(code, message, null);
    }
    public static <T> ResponseMessage<T> error(int code, String message, T data){
        return new ResponseMessage(code, message, data);
    }
}
