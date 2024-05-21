package com.apple.shop.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;


    public void addOrder(String itemName, Integer price, Integer count, Long memberId){
        Sales orderNew = new Sales();
        orderNew.setItemName(itemName);
        orderNew.setPrice(price);
        orderNew.setCount(count);
        orderNew.setMemberId(memberId);
        salesRepository.save(orderNew);

    }

    public List<Sales> allOrder(){
        return salesRepository.findAll();

    }
}
