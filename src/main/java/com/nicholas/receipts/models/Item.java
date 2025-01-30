package com.nicholas.receipts.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

    @Entity
    @Table(name="ITEM")
    @Data
    public class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long itemId;
        @Column
        String shortDescription;
        @Column
        BigDecimal price;
        @ManyToOne
        @JoinColumn(name="receiptId")
        @JsonBackReference
        Receipt receipt;
    }

