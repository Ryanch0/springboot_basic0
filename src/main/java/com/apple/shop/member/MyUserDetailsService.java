package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DB에서 username을 가진 유저를 찾아와서
      var result =  memberRepository.findByUsername(username);
        if (result.isEmpty()){
            throw new UsernameNotFoundException("wrong username");
        }
        var user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        var a = new CustomUser(user.getUsername(), user.getPassword(),authorities);
        a.displayName = user.getDisplayName();
        return a;
    }
}

//User 클래스를 커스터마이즈
class CustomUser extends User {
    public String displayName;
    public CustomUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}