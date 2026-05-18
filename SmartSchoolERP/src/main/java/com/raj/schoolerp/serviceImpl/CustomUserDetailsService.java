package com.raj.schoolerp.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	Optional<Users> userByUserName = usersRepo.findUserByUserName(username);

		if (userByUserName.isEmpty()) {

			throw new UsernameNotFoundException("User not found");
		}

		return new User(userByUserName.get().getUserName(), userByUserName.get().getPassword(),
				AuthorityUtils.createAuthorityList(userByUserName.get().getRole().toString()));
	}
}