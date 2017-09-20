package com.rarc.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
 
    private ApiError() {
        timestamp = LocalDateTime.now();
    }
 
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }
 
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
 
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public String convertToJson() throws JsonProcessingException {
        if (this == null) {
            return null;
        }
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
    
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setLocalDateTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}