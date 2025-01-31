package com.nicholas.receipts.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
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
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "receipt")
    @JsonManagedReference
    @JsonProperty("items")
    private List<Item> items;
    @Column
    
    private BigDecimal total;
    
    public Receipt(Long id, String retailer, LocalDate date, LocalTime time, List<Item> items, BigDecimal total){
        this.receiptId = id;
        this.retailer = retailer;
        this.date = date;
        this.time = time;
        this.items = items;
        this.total = total;
    }


}
