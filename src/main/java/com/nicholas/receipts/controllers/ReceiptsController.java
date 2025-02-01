package com.nicholas.receipts.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nicholas.receipts.exception.BadReceiptRequestException;
import com.nicholas.receipts.exception.ReceiptNotFoundException;
import com.nicholas.receipts.models.PointsResponse;
import com.nicholas.receipts.models.Receipt;
import com.nicholas.receipts.models.ReceiptResponse;
import com.nicholas.receipts.service.ReceiptService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




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

    
    @PostMapping({"/receipts","/receipts/"})
    public ReceiptResponse postMethodName(@Valid @RequestBody Receipt entity, BindingResult br) {
        if(br.hasErrors()){
            throw new BadReceiptRequestException();
        }

        receiptService.saveReceipt(entity);

        ReceiptResponse response = new ReceiptResponse();
        response.setId(Long.toString(entity.getReceiptId()));

        return response;
    }

    @GetMapping({"/receipts/{id}","/receipts/{id}/"})
    public Receipt getReceipt(@PathVariable(name="id") String id) {
        
        Receipt receipt = receiptService.getReceipt(Long.parseLong(id));
        if(Objects.isNull(receipt)){
            throw new ReceiptNotFoundException();
        }
        return receipt;
    }

    @GetMapping({"/receipts/{id}/points","/receipts/{id}/points/"})
    public PointsResponse getMethodName(@PathVariable(name="id") String id) {
        PointsResponse pr = new PointsResponse();
        Receipt receipt = receiptService.getReceipt(Long.parseLong(id));
        if(Objects.isNull(receipt)){
            throw new ReceiptNotFoundException();
        }
        pr.setPoints(receiptService.calculatePoints(receipt));
        return pr;
    } 
    
    
    
}
