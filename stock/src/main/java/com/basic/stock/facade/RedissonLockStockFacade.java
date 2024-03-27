package com.basic.stock.facade;

import com.basic.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {
    private final RedissonClient redissonClient;
    private final StockService stockService;

    public void decreaseStock(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        try {
            boolean available = lock.tryLock(15, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println(" lock 획득 실패");
                return;
            }
            stockService.decreaseStock(id, quantity);
        } catch (Exception e) {
            System.out.println(" 재고 감소 실패");
        } finally {
            lock.unlock();
        }
    }
}
