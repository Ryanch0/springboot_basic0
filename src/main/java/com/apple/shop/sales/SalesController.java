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
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final ItemRepository itemRepository;
    private final SalesService salesService;
    private final MemberRepository memberRepository;

    @GetMapping("/order/{id}")
    String order(Authentication auth, Model model, @PathVariable Long id) {
        if(auth != null && auth.isAuthenticated()) {
           var result = itemRepository.findById(id);
            model.addAttribute("product", result);
            return "order.html";
        } else {
            return "/login";
        }
    }

    @PostMapping("/orderDetail")
    String orderDetail (String itemName, Integer price, Integer count,
                       Authentication auth ){
        salesService.addOrder(itemName,price,count,auth);
        return "redirect:/list/page/1";
    }

    @GetMapping("/orderList")
    @ResponseBody
    public List<SalesDto> getOrder (){
        List<Sales> result = salesService.allOrder();
        List<SalesDto> data = new ArrayList<>();
        for (Sales a : result) {
            SalesDto dto = new SalesDto(a.getItemName(), a.getPrice(), a.getCount(),
                    a.getMember().getUsername(), a.getMember().getDisplayName(), a.getCreated());
            data.add(dto);
        }
        return  data;
    }

    //@OneToMany test
    @GetMapping("/order/all")
    String getOrderAll(){
       var result =  memberRepository.findById(5L);
       System.out.println(result.get().getSales());
        return "redirect:/list/page/1";
    }


}

class SalesDto {
    public String itemName;
    public Integer price;
    public Integer count;
    public String username;
    public String displayName;
    public LocalDateTime created;
    SalesDto(String a, Integer b, Integer c, String d, String e, LocalDateTime f){
        this.itemName = a;
        this.price = b;
        this.count = c;
        this.username = d;
        this.displayName = e;
        this.created = f;
    }
}
