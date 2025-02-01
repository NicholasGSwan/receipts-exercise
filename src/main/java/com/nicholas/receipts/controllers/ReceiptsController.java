package com.nicholas.receipts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicholas.receipts.models.Receipt;
import com.nicholas.receipts.models.ReceiptResponse;
import com.nicholas.receipts.service.ReceiptService;
import org.springframework.web.bind.annotation.RequestParam;




@RestController

public class ReceiptsController {

    
    private final ReceiptService receiptService;
    
    public ReceiptsController (ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello this project works";
    }

    //TODO: set bad request response
    @PostMapping({"/receipts","/receipts/"})
    public ReceiptResponse postMethodName(@RequestBody Receipt entity) {
        
        receiptService.saveReceipt(entity);

        ReceiptResponse response = new ReceiptResponse();
        response.setId(Long.toString(entity.getReceiptId()));

        return response;
    }

    @GetMapping({"/receipts/{id}","/receipts/{id}/"})
    public Receipt getReceipt(@PathVariable(name="id") String id) {
        
        Receipt receipt = receiptService.getReceipt(Long.parseLong(id));
        //TODO: can return null, need to handle
        return receipt;
    }

    @GetMapping({"/receipts/{id}/points","/receipts/{id}/points/"})
    public String getMethodName(@PathVariable(name="id") String id) {
        return new String();
    }
    
    
    
    
    
}
