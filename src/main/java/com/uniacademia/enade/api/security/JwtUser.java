package com.uniacademia.enade.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.UserType;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class JwtUser implements UserDetails {
	private static final long serialVersionUID = -268046329085485932L;

	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public static JwtUser authenticationTojwtUser(Authentication authentication) {
		JwtUser jwt = new JwtUser();
		jwt.setId(authentication.getId());
		jwt.setEmail(authentication.getEmail());
		jwt.setPassword(authentication.getPassword());

		List<GrantedAuthority> authorities = mapToGrantedAuthorities(authentication.getUser().getUserType());
		jwt.setAuthorities(authorities);

		return jwt;
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(UserType userType) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userType.getName()));

		return authorities;
	}
}
