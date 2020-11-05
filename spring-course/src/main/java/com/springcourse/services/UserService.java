package com.springcourse.services;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
import com.springcourse.domain.repositories.UserRepository;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.services.util.HashUtil;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		
		String hashPassword = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hashPassword);
		
		return userRepository.save(user);
	}
	
	public User update(User user) {
		
		String hashPassword = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hashPassword);
		
		return userRepository.save(user);
	}
	
	public User findById(Long id) throws NotFoundException {
		Optional<User> opUser = userRepository.findById(id);
		
		if(!opUser.isPresent()) {
			throw new NotFoundException("Não existe um usuário com id " + id + ".");
		}
		
		return opUser.get();
	}
	
	public List<User> findAll(){
		
		return userRepository.findAll();
	}
	
	public PageModel<User> findAllPageable(Pageable pageable){
		Page<User> page = userRepository.findAll(pageable); 		
		PageModel<User> userPage = new PageModel<>(page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getSort(), page.getContent());
		
		return userPage;
	}
	
	public User login(String email, String password) throws NotFoundException {
		
		String hashPassword = HashUtil.getSecureHash(password);
		
		Optional<User> opUser = userRepository.login(email, hashPassword);
		
		if(!opUser.isPresent()) {
			throw new NotFoundException("Não existe um usuário com e-mail " + email + " ou senha " + password + ".");
		}
		
		return opUser.get();
	}

	public Integer updateRole(Long id, Role role) {
		
		return userRepository.updateRole(id, role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opUser = userRepository.findByEmail(username);
		
		if(!opUser.isPresent()) {
			throw new UsernameNotFoundException("Não existe um usuário com e-mail " + username + ".");
		}
		
		User user = opUser.get();

		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
		
		org.springframework.security.core.userdetails.User userSec = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
		
		return userSec;
	}
	
}
