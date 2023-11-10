//package me.hellonayeon.backend.security;
package com.example.backend.security;

import java.util.Date;
import java.util.Optional;

//import me.hellonayeon.backend.member.dao.MemberDao;
import com.example.backend.member.repository.MemberRepository;
//import me.hellonayeon.backend.member.domain.Member;
import com.example.backend.member.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // username = User (id)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl loadUserByUsername " + new Date());

        Optional<Member> member = memberRepository.findById(username);
        if (member == null) {
            throw new UsernameNotFoundException(String.format("'%s'는 존재하지 않는 사용자입니다.", username));
        }

        return new com.example.backend.security.UserDetailsImpl(member);
    }
}
