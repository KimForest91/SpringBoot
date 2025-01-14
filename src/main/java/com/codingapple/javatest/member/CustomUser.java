package com.codingapple.javatest.member;

import java.util.Collection;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;

public class CustomUser extends User {
    public String displayName;

    public CustomUser(
        String username, 
        String password, 
        Collection<? extends GrantedAuthority> authorities
    ) {
        super();
    }
}
