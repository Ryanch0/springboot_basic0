package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;


    public void addOrder(String itemName, Integer price, Integer count,
                         Authentication auth){
        Sales orderNew = new Sales();
        orderNew.setItemName(itemName);
        orderNew.setPrice(price);
        orderNew.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId(user.id);
        orderNew.setMember(member);
        salesRepository.save(orderNew);

    }

    public List<Sales> allOrder(){
        return salesRepository.customFindAll();

    }
}
