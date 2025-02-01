package com.nicholas.receipts.exception;

public class BadReceiptRequestException extends RuntimeException {

    public BadReceiptRequestException(){
        super("The receipt is invalid");
    }
}
