package com.talentica.web.service;

import com.talentica.web.model.User;
import com.talentica.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	public User findOne(long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()){
			String role = user.get().getRole().name();
			List<String> roleList = new ArrayList<>();
			roleList.add(role);
			List<SimpleGrantedAuthority> roles = roleList.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
