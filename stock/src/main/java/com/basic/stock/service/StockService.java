package com.basic.stock.service;

import com.basic.stock.entity.Stock;
import com.basic.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;

   // @Transactional(propagation = Propagation.REQUIRES_NEW) // 트랜젝션을 새로 열어서 새로운 em 매니저, 영속성컨텍스트 시도한다.
   @Transactional
    public synchronized void decreaseStock(Long id, Long quantity) {
        // stock 조회
        Stock stock = repository.findById(id).orElseThrow(); // null 이면 에러를 출력
        // 재고 감소
        stock.decreaseStock(quantity);
        // 바로 갱신된 값을 db에 저장하려며
        repository.saveAndFlush(stock);
    }
}
