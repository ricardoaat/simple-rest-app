package com.rarc.config;

import com.rarc.rest.ApiError;

import io.jsonwebtoken.JwtException;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e, "Token Jwt Exception");
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e, "Runtime Exception");
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex, String message){
        response.setStatus(status.value());
        response.setContentType("application/json");
        ApiError apiError = new ApiError(status, message, ex);
        try {
            String json = apiError.convertToJson();
            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}