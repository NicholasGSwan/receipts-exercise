package com.nicholas.receipts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nicholas.receipts.models.Item;
import com.nicholas.receipts.models.Receipt;
import com.nicholas.receipts.repository.ReceiptRepository;
import com.nicholas.receipts.service.ReceiptServiceImplV1;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReceiptServiceImplV1Tests {

    @InjectMocks
    private ReceiptServiceImplV1 receiptService;

    @Mock
    private ReceiptRepository receiptRepository;

    @Test
    public void testGetReceiptById(){
        long receiptId = 1L;

        Item mockItem = new Item(1L, "Mountain Dew 12PK", new BigDecimal("6.49"));
        List<Item> mockItems = new ArrayList<>();
        mockItems.add(mockItem);
        Optional<Receipt> mockReceipt = Optional.of(new Receipt(receiptId ,"Target",LocalDate.parse("2022-01-01"),LocalTime.parse("13:01"), mockItems, new BigDecimal("6.49")));

        Mockito.when(receiptRepository.findById(receiptId)).thenReturn(mockReceipt);
        Receipt result = receiptService.getReceipt(receiptId);

        assertNotNull(result);
        assertEquals(receiptId, result.getReceiptId());




    }

    @Test
    public void testGetRetailerNamePoints_AllAlpha(){
        Receipt receipt = new Receipt();
        receipt.setRetailer("Target");
        long points = receiptService.getRetailerNamePoints(receipt);

        assertEquals(6L, points);
    }

    @Test
    public void testGetRetailerNamePoints_SymbolsAndSpaces(){
        Receipt receipt = new Receipt();
        receipt.setRetailer("M&M Corner Market");
        long points = receiptService.getRetailerNamePoints(receipt);

        assertEquals(14L, points);
    }

    @Test
    public void testGetRoundDollarAmountPoints_hasRoundDollarAmount(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10"));
        long points = receiptService.getRoundDollarAmountPoints(receipt);

        assertEquals(50L, points);
    }

    @Test
    public void testGetRoundDollarAmountPoints_doesNotHaveRoundDollarOneDecimalPlace(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.1"));
        long points = receiptService.getRoundDollarAmountPoints(receipt);

        assertEquals(0L, points);
    }

    @Test
    public void testGetRoundDollarAmountPoints_doesNotHaveRoundDollarTwoDecimalPlace(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.15"));
        long points = receiptService.getRoundDollarAmountPoints(receipt);

        assertEquals(0L, points);
    }

    @Test
    public void testGetMultiplesOf25_isMultiplesOf25NoDecimal(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10"));
        long points = receiptService.getMultiplesOf25(receipt);

        assertEquals(25L, points);
        
    }

    @Test
    public void testGetMultiplesOf25_isMultiplesOf25OneDecimal(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.0"));
        long points = receiptService.getMultiplesOf25(receipt);
        receipt.setTotal(new BigDecimal("10.5"));
        long points2 = receiptService.getMultiplesOf25(receipt);

        assertEquals(25L, points);
        assertEquals(25L, points2);
        
    }

    @Test
    public void testGetMultiplesOf25_isMultiplesOf25TwoDecimal(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.00"));
        long points = receiptService.getMultiplesOf25(receipt);

        receipt.setTotal(new BigDecimal("10.25"));
        long points2 = receiptService.getMultiplesOf25(receipt);

        receipt.setTotal(new BigDecimal("10.50"));
        long points3 = receiptService.getMultiplesOf25(receipt);

        receipt.setTotal(new BigDecimal("10.75"));
        long points4 = receiptService.getMultiplesOf25(receipt);

        assertEquals(25L, points);
        assertEquals(25L, points2);
        assertEquals(25L, points3);
        assertEquals(25L, points4);
        
    }

    @Test
    public void testGetMultiplesOf25_notMultiplesOf25OneDecimal(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.1"));
        long points = receiptService.getMultiplesOf25(receipt);

        assertEquals(0L, points);
    }

    @Test
    public void testGetMultiplesOf25_notMultiplesOf25TwoDecimal(){
        Receipt receipt = new Receipt();
        receipt.setTotal(new BigDecimal("10.20"));
        long points = receiptService.getMultiplesOf25(receipt);
        receipt.setTotal(new BigDecimal("10.01"));
        long points2 = receiptService.getMultiplesOf25(receipt);

        assertEquals(0L, points);
        assertEquals(0L, points2);
    }

    @Test
    public void testGetPairsOfItems(){
        Receipt receipt = new Receipt();
        List<Item> items = new ArrayList<>();
        //no items
        receipt.setItems(items);
        long points = receiptService.getPairsOfItemsPoints(receipt);
        //one item
        receipt.getItems().add(new Item());
        long points2 = receiptService.getPairsOfItemsPoints(receipt);
        // two items
        receipt.getItems().add(new Item());
        long points3 = receiptService.getPairsOfItemsPoints(receipt);
        // three items
        receipt.getItems().add(new Item());
        long points4 = receiptService.getPairsOfItemsPoints(receipt);
        // four items
        receipt.getItems().add(new Item());
        long points5 = receiptService.getPairsOfItemsPoints(receipt);

        assertEquals(0L, points);
        assertEquals(0L, points2);
        assertEquals(5L, points3);
        assertEquals(5L, points4);
        assertEquals(10L, points5);
    }

    @Test
    public void testGetItemsIfDescMultOf3(){
        Receipt receipt = new Receipt();
        List<Item> items = new ArrayList<>();
        
        items.add(new Item(1L, "Emils Cheese Pizza", new BigDecimal("12.25")));
        items.add(new Item(2L, "   Klarbrunn 12-PK 12 FL OZ  ", new BigDecimal("12")));
        items.add(new Item(3L, "Mountain Dew 12PK ", new BigDecimal("6.49")));
        items.add(new Item(4L, "Mountain Dew 12PK", new BigDecimal("6.49")));
        items.add(new Item(5L, "Knorr Creamy Chickens", new BigDecimal(".01")));
        items.add(new Item(5L, "Knorr Creamy Chicken ", new BigDecimal(".01")));

        receipt.setItems(items);

        long points = receiptService.getItemsIfDescriptionIsMultOf3(receipt);

        assertEquals(7l, points);
    }

    @Test
    public void testGetPointsIfDayIsOdd(){
        Receipt receipt = new Receipt();
        //even date
        receipt.setDate(LocalDate.of(2025, 1, 10));
        long points = receiptService.getPointsIfDayIsOdd(receipt);

        receipt.setDate(LocalDate.of(2025, 1, 11));
        long points2 = receiptService.getPointsIfDayIsOdd(receipt);

        assertEquals(0L, points);
        assertEquals(6L, points2);
    }

    @Test
    public void testGetPointsIfBetween2and4pm(){
        Receipt receipt = new Receipt();

        receipt.setTime(LocalTime.of(10, 0));
        long points = receiptService.getPointsIfBetween2and4pm(receipt);

        receipt.setTime(LocalTime.of(14, 15));
        long points2 = receiptService.getPointsIfBetween2and4pm(receipt);

        assertEquals(0L, points);
        assertEquals(10L, points2);

    }
}
