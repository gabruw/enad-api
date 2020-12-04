package com.uniacademia.enade.api.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.security.JwtUser;
import com.uniacademia.enade.api.service.AuthenticationService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Authentication> authentication = this.authenticationService.findByEmail(email);
		if (authentication.isPresent()) {
			return JwtUser.authenticationTojwtUser(authentication.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}
}
