package com.nicholas.receipts.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.nicholas.receipts.models.Receipt;
import com.nicholas.receipts.repository.ReceiptRepository;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController

public class ReceiptsController {

    
    ReceiptRepository receiptRepository;
    
    public ReceiptsController (ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }

    @GetMapping("/")
    public String index() {
        return "Hello this project works";
    }

    @PostMapping("/receipts")
    public Receipt postMethodName(@RequestBody Receipt entity) {
        
        receiptRepository.save(entity);

        return entity;
    }
    
    
    
}
