//package me.hellonayeon.backend.security;
package com.example.backend.security;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import com.example.backend.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private final Optional<Member> member;

    public UserDetailsImpl(Optional<Member> member) {
        this.member = member;
    }

    public Optional<Member> getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public String getPassword() {
        return member.isPresent() ? member.get().getPwd() : null;
    }

    @Override
    public String getUsername() {
        return member.isPresent() ? member.get().getId() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
