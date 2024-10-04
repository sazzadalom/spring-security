package com.alom.login.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alom.login.model.MyUserDetails;
import com.alom.login.model.Users;
import com.alom.login.repository.UserRepository;

@Service
public class MyUserDetailService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users= userRepository.findByUsername(username);
		if(Objects.isNull(users)) {
			System.out.println(username + " USER DID NOT FOUNDED!");
			throw new UsernameNotFoundException(username + " USER DID NOT FOUNDED!");
		}
		
		return new MyUserDetails(users);
	}

}
