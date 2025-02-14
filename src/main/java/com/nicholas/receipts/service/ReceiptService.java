package com.nicholas.receipts.service;

import com.nicholas.receipts.models.Receipt;

public interface ReceiptService {
    public abstract Receipt saveReceipt(Receipt receipt);
    public abstract Receipt getReceipt(Long id);
    public abstract Receipt getReceipt(String id);
    public abstract long calculatePoints(Receipt receipt);
}
