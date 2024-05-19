package com.apple.shop.item;
import com.apple.shop.Notice;
import com.apple.shop.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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


    //    pagination
    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page) {
        Slice<Item> result = itemService.pageItem(page);
        model.addAttribute("items", result); //타임리프 문법
        return "list.html";
    }
//    @GetMapping("/list")
////    Model 변수는 타임리프 문법 변수
//    String list(Model model) {
//        List<Item> result= itemService.dbItem(); //서비스레이어 연습
//        model.addAttribute("items", result); //타임리프 문법
//        return "list.html";
//    }

    @GetMapping("/write")
    String write(Authentication auth) {
        if(auth != null && auth.isAuthenticated()) {
            return "write.html";
        } else {
            return "redirect:/list/page/1";
        }
    }

    @PostMapping("/add")
    String addPost(String title, Integer price, String username) {
        itemService.saveItem(title, price, username);   //서비스레이어 연습
        return "redirect:/list/page/1";
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
                return "redirect:/list/page/1";
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
            return "redirect:/list/page/1";
        }
    }

    @PostMapping("/setModify/{id}")
    String setModify(@PathVariable Integer id, String title, Integer price) {
        itemService.modifyItem(id,title, price);   //서비스레이어 연습
        return "redirect:/list/page/1";
    }


    @DeleteMapping("/delete/{id}")
     ResponseEntity<String> deleteItem(@PathVariable Integer id){
        try {
            itemService.deleteService(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Delete conflict!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
    }





}
