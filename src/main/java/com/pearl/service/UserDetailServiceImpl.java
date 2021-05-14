package com.pearl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.domain.CustomUser;
import com.pearl.domain.MemberVO;
import com.pearl.mapper.MemberMapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Transactional
	public int joinUser(MemberVO member) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setMemPass(passwordEncoder.encode(member.getMemPass()));
        return mapper.insert(member);
    }

    @Override
    public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
    	MemberVO member = mapper.get(memEmail);
        return new CustomUser(member);
    }
    
}
