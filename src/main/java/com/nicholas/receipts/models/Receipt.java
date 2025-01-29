package com.nicholas.receipts.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Receipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;
    @Column
    private String retailer;
    @Column
    @JsonProperty("purchaseDate")
    private LocalDate date;
    @Column
    @JsonProperty("purchaseTime")
    private LocalTime time;
    @OneToMany(mappedBy = "receipt")
    @JsonProperty("items")
    private List<Item> items;
    @Column
    private BigDecimal total;
    
    @Entity
    @Table
    @Data
    private static class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long itemId;
        @Column
        String shortDescription;
        @Column
        BigDecimal price;
        @ManyToOne
        @JoinColumn(name="receipt_id", nullable = false)
        Receipt receipt;
    }


}
