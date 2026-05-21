package com.raj.schoolerp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		System.out.println(user.getPassword());

		return new User(user.getUserName(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}
}