package com.nicholas.receipts.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Receipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String receiptId;
    String retailer;
    LocalDate date;
    LocalTime time;
    List<Item> items;

    
    @Entity
    private static class Item {
        @Id
        @GeneratedValue
        String itemId;
        String shortDescription;
        BigDecimal price;
    }


}
