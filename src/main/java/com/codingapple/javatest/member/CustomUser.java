package com.codingapple.javatest.member;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class CustomUser extends User {
    public String displayName;

    public CustomUser(
        String username, 
        String password, 
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
