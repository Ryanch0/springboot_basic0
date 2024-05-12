package com.apple.shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;


@Controller
public class BasicController {
    @GetMapping("/")
    String hi(){
        return "forward:/index.html";
    }


    @GetMapping("/mypage")
    @ResponseBody
    String intro(){
        return "마이페이지임";
    }

    @GetMapping("/date")
    @ResponseBody
    String date(){
        return LocalDateTime.now().toString();
    }


}


