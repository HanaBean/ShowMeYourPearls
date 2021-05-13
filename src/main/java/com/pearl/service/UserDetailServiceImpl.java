package com.pearl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.domain.MemberEntity;
import com.pearl.domain.MemberRepository;
import com.pearl.domain.MemberVO;
import com.pearl.domain.Role;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private MemberRepository repository;
/*시큐리티 적용*/
	
	@Transactional
	 public Long joinUser(MemberVO member) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setMemPass(passwordEncoder.encode(member.getMemPass()));
        return repository.save(member.toEntity()).getMemNum();
    }

    @Override
    public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = repository.findByMemEmail(memEmail);
        MemberEntity userEntity = userEntityWrapper.get();
        String role = userEntity.getMemLevel();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(role)) {
        	authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getMemName(), userEntity.getMemPass(), authorities);
    }
}
