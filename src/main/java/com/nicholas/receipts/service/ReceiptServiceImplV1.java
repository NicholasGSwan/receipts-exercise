package com.nicholas.receipts.service;

import org.springframework.stereotype.Component;

import com.nicholas.receipts.models.Receipt;
import com.nicholas.receipts.repository.ReceiptRepository;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Component
@Setter
@AllArgsConstructor
public class ReceiptServiceImplV1 implements ReceiptService {

    private ReceiptRepository recRep;

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        recRep.save(receipt);
        return receipt;
    }

    public int calculatePoints(Receipt receipt){
        int pointTotal = 0;


        return pointTotal;
    }

}
