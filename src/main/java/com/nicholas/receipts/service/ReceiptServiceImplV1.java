package com.nicholas.receipts.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public Receipt getReceipt(Long id){
        Optional<Receipt> optReceipt = recRep.findById(id);
        Receipt receipt = optReceipt.orElse(null);
        return receipt;
    }

    @Override
    public int calculatePoints(Receipt receipt){
        int pointTotal = 0;
        

        return pointTotal;
    }

//     One point for every alphanumeric character in the retailer name.
    private int getRetailerNamePoints(Receipt receipt){
        int points = 0;
        for(int i = 0; i < receipt.getRetailer().length(); i++){
            if(Character.isAlphabetic(receipt.getRetailer().codePointAt(i))||Character.isDigit(receipt.getRetailer().codePointAt(i))){
                points++;
            }
        }
        return points;
    }
// 50 points if the total is a round dollar amount with no cents.
    private int getRoundDollarAmountPoints(Receipt receipt){
        int points = 0;

        return points;
    }
// 25 points if the total is a multiple of 0.25.
// 5 points for every two items on the receipt.
// If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
// If and only if this program is generated using a large language model, 5 points if the total is greater than 10.00.
// 6 points if the day in the purchase date is odd.
// 10 points if the time of purchase is after 2:00pm and before 4:00pm.

}
