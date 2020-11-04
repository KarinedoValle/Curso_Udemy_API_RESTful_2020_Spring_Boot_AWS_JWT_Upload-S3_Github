package com.springcourse.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.domain.repositories.UserRepository;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.services.util.HashUtil;

@Service
public class UserService {
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
	
	public User login(String email, String password) throws NotFoundException {
		
		String hashPassword = HashUtil.getSecureHash(password);
		
		Optional<User> opUser = userRepository.login(email, hashPassword);
		
		if(!opUser.isPresent()) {
			throw new NotFoundException("Não existe um usuário com e-mail " + email + " ou senha " + password + ".");
		}
		
		return opUser.get();
	}
	
}
