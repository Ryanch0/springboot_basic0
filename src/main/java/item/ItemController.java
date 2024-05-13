package item;
import com.apple.shop.Notice;
import com.apple.shop.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final NoticeRepository noticeRepository;
    private final ItemService itemService;

    @GetMapping("/list")
//    Model 변수는 타임리프 문법 변수
    String list(Model model) {
        List<Item> result= itemService.dbItem(); //서비스레이어 연습
        model.addAttribute("items", result); //타임리프 문법
        System.out.println(result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);   //서비스레이어 연습
        return "redirect:/list";
    }



    @GetMapping("/notice")
    String notice(Model model) {
        List<Notice> result = noticeRepository.findAll();
        model.addAttribute("title", result);
        return "notice.html";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model) {
            Optional<Item> result = itemService.itemId(id);  //서비스레이어 연습
            if(result.isPresent()){
                model.addAttribute("detail", result);
                return "detail.html";
            } else {
                return "redirect:/list";
            }
    }

//    수정기능
    @GetMapping("/modify/{id}")
    String modify(@PathVariable Integer id, Model model) {
        Optional<Item> result = itemService.itemId(id);  //서비스레이어 연습
        if(result.isPresent()){
            model.addAttribute("modify", result);
            return "modify.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/setModify/{id}")
    String setModify(@PathVariable Integer id, String title, Integer price) {
        itemService.modifyItem(id,title, price);   //서비스레이어 연습
        return "redirect:/list";
    }


}
