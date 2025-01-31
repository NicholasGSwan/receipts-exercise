package com.nicholas.receipts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholas.receipts.models.Receipt;


public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
