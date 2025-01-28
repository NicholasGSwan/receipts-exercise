package com.nicholas.receipts.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ReceiptsController {

    @GetMapping("/")
    public String index() {
        return "Hello this project works";
    }
    
}
