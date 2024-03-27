package com.basic.stock.transaction;

import com.basic.stock.service.StockService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionService {
    private final StockService stockService;

    public void decrease(Long id, Long quantity) {
        startTransaction();
        stockService.decreaseStock(id, quantity);
        endTransaction();
    }

    private void startTransaction() {
        System.out.println("트랜잭션 시작");
    }

    private void endTransaction() {
        System.out.println("트랜잭션 끝");
    }
}
