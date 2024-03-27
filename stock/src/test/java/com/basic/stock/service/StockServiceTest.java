package com.basic.stock.service;


import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private OptimisticLockStockService service;
    @Autowired
    private StockRepository repository;

    @BeforeEach
    public void insert() {
        // 상품 아이디가 1 이고 수량이 100 인 재고 한개 생성
        Stock stock = new Stock(1L, 100L);
        repository.saveAndFlush(stock);

    }

    @Test
    public void decreaseTest() {
        service.decreaseStock(1L, 1L); // 상품id 1 인 상품의 수량을 1씩 감소
        Stock stock = repository.findById(1L).orElseThrow();
        System.out.println("stock = " + stock);
        System.out.println("count = " + stock.getQuantity());
        Assertions.assertThat(stock.getQuantity()).isEqualTo(99);
    }

    @Test
    public void orderSameTime100Stock() throws InterruptedException {
        int thCnt = 100;
        // 동시에 32개 쓰레드 관리
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        // 100개의 요청이 모두 끝날 때 까지 기다려야하므로 CountDownLatch 통해서
        // 다른 쓰레드에서 수행되는 작업이 완료될 때까지 대기할 수 있도록 도와주는 클래스
        CountDownLatch latch = new CountDownLatch(thCnt);
        for (int i = 0; i < thCnt; i += 1) {
            executorService.submit(() -> {
                try {
                    service.decreaseStock(1L, 1L);
                } catch (Exception e) {
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Stock stock = repository.findById(1L).orElseThrow();
        System.out.println("stock = " + stock);
        Assertions.assertThat(stock.getQuantity()).isEqualTo(0);
    }
}
