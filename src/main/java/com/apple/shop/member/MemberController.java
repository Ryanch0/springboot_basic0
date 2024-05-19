package com.apple.shop.member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder; // Dependency Injection example


    @GetMapping("/register")
    String register(Authentication auth) {
        if( auth != null && auth.isAuthenticated() ){
            return "redirect:/list/page/1";
        }
            return "register.html";
    }

    @PostMapping("/signup")
    String signup (String displayName, String username, String password){
       memberService.saveAccount(displayName,username,password);
        return "redirect:/list/page/1";
    }

    @GetMapping("/login")
    String login(){
        return "login.html";
    }

    @PostMapping("/signin")
    String signin(String username, String password){

        return "redirect:/list/page/1";
    }

    @GetMapping("/my-page")
    String mypage(Authentication auth){
        System.out.println(auth);
        System.out.println(auth.getName()); //get username
        System.out.println(auth.isAuthenticated()); //verify whether user login or not
        CustomUser result = (CustomUser) auth.getPrincipal(); // customizing class
        System.out.println(result.displayName);
        if(auth.isAuthenticated() == true){
            return "mypage.html";
        } else {
            return "redirect:/list/page/1";
        }

    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser() {
       var a =  memberRepository.findById(1L);
       var result = a.get();
       var data = new MemberDto(result.getUsername(), result.getDisplayName(), result.getId());
       return data;
    }
}
//DTO training
class MemberDto {
    public String username;
    public String displayName;
    public Long id;
    MemberDto(String a, String b){
        this.username = a;
        this.displayName = b;
    }
    MemberDto(String a, String b, Long id){
        this.username = a;
        this.displayName = b;
        this.id = id;
    }
}
