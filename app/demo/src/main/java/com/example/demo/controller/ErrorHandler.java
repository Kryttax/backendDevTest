package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ResponseEntity<>(
                        "Route could not be processed",
                        HttpStatus.NOT_FOUND);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new ResponseEntity<>(
                        "Product not found",
                        HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(
                "Unkown error",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}