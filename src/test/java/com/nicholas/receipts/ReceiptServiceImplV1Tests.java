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
import com.nicholas.receipts.service.ReceiptService;
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
    public void testGetRetailerNamePoints(){
        Receipt receipt = new Receipt();
        receipt.setRetailer("Target");
    }
}
