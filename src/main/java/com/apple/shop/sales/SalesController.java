package com.apple.shop.sales;

import com.apple.shop.item.ItemRepository;
import com.apple.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final SalesService salesService;

    @GetMapping("/order/{id}")
    String order(Authentication auth, Model model, @PathVariable Long id) {
        if(auth != null && auth.isAuthenticated()) {
           var result = itemRepository.findById(id);
          var result2 = memberRepository.findByUsername(auth.getName());
            model.addAttribute("product", result);
            model.addAttribute("userinfo", result2);
            return "order.html";
        } else {
            return "/login";
        }
    }

    @PostMapping("/orderDetail")
    String orderDetail (String itemName, Integer price, Integer count, Long memberId){
        salesService.addOrder(itemName,price,count,memberId);
        return "redirect:/orderList";
    }

    @GetMapping("/orderList")
    String orderList(Model model){
        List<Sales> result = salesService.allOrder();
        model.addAttribute("orders", result);
        return "orderList.html";
    }


}
