package com.apple.shop.member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void saveAccount(String displayName, String username, String password) {
        var result = memberRepository.findByUsername(username);
        if (result.isPresent()){
            throw new IllegalArgumentException("Username is already using");
        }
        if (username == null || username.length() < 4) {
            throw new IllegalArgumentException("Username must be at least 5 characters long");
        }
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 5 characters long");
        }

        Member addAccount = new Member();
        var encoder = new BCryptPasswordEncoder().encode(password);
        addAccount.setDisplayName(displayName);
        addAccount.setUsername(username);
        addAccount.setPassword(encoder);
        memberRepository.save(addAccount);
    }

}
