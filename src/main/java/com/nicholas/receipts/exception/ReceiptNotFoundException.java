package com.nicholas.receipts.exception;

public class ReceiptNotFoundException extends RuntimeException{

    public ReceiptNotFoundException(){
        super("No receipt found for that ID.");
    }

}
