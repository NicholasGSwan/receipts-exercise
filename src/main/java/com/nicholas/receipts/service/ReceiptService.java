package com.nicholas.receipts.service;

import com.nicholas.receipts.models.Receipt;

public interface ReceiptService {
    public abstract Receipt saveReceipt(Receipt receipt);
    public abstract Receipt getReceipt(Long id);
    public abstract int calculatePoints(Receipt receipt);
}
