package com.apple.shop.member;
import lombok.RequiredArgsConstructor;
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
    String register() {
        return "register.html";
    }

    @PostMapping("/signup")
    String signup (String displayName, String username, String password){
       memberService.saveAccount(displayName,username,password);
        return "redirect:/list";
    }
}
