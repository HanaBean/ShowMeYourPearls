package com.pearl.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.pearl.service.UserDetailServiceImpl;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
    private UserDetailServiceImpl login;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String)authentication.getPrincipal();
        String userPassword = (String)authentication.getCredentials();

        //로그인 로직 구현
        UserDetails user = login.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(user.getUsername(), userPassword, user.getAuthorities());
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
