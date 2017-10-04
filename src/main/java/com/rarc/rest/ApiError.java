package com.rarc.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

public class ApiError implements Serializable {

    private final static long serialVersionUID = -5360953025237602687L;
    
    private HttpStatus status;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private String exMessage;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }
 
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
    
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
        this.exMessage = ex.getCause().getLocalizedMessage();
    }
 
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(this);
    }

    public HttpStatus getStatus(){
        return status;
    }
    
    public void setStatus(HttpStatus status){
        this.status = status;
    }
    
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getDebugMessage(){
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage){
        this.debugMessage = debugMessage;
    }

    public String getexMessage(){
        return exMessage;
    }

    public void setexMessage(String exMessage){
        this.exMessage = exMessage;
    }    

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setLocalDateTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}