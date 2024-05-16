package com.apple.shop.member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder; // Dependency Injection example


    @GetMapping("/register")
    String register(Authentication auth) {
        if(auth.isAuthenticated() == true){
            return "redirect:/list";
        }else {
            return "register.html";
        }
    }

    @PostMapping("/signup")
    String signup (String displayName, String username, String password){
       memberService.saveAccount(displayName,username,password);
        return "redirect:/list";
    }

    @GetMapping("/login")
    String login(){
        return "login.html";
    }

    @PostMapping("/signin")
    String signin(String username, String password){

        return "redirect:/list";
    }

    @GetMapping("/my-page")
    String mypage(Authentication auth){
        System.out.println(auth);
        System.out.println(auth.getName()); //get username
        System.out.println(auth.isAuthenticated()); //verify whether user login or not
        if(auth.isAuthenticated() == true){
            return "mypage.html";
        } else {
            return "redirect:/list";
        }

    }
}
