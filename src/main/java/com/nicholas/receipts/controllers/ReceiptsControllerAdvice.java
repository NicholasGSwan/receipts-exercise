package com.nicholas.receipts.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nicholas.receipts.exception.BadReceiptRequestException;
import com.nicholas.receipts.exception.ReceiptNotFoundException;

@RestControllerAdvice
public class ReceiptsControllerAdvice {

    @ExceptionHandler(value = {BadReceiptRequestException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badReceiptResponseHandler(RuntimeException ex){
        
        return new BadReceiptRequestException().getMessage();
    }

    @ExceptionHandler(value = ReceiptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String receiptNotFoundHandler(ReceiptNotFoundException ex){
        return ex.getMessage();
    }
}
