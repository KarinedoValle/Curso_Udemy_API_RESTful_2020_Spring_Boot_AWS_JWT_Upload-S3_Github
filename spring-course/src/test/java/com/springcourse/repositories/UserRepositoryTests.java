package com.springcourse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
import com.springcourse.domain.repositories.UserRepository;

@SpringBootTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	public void saveTest () {
		User user = new User(null, "Test", "test@gmail.com", "1234", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
	}

	@Test
	public void updateTest() {
		Optional<User> opFoundUser = userRepository.findById(1L);
		User foundUser = opFoundUser.get();
		
		foundUser.setName("Test test");
		
		User updatedUser = userRepository.save(foundUser);
		
		assertThat(updatedUser.getName()).isEqualTo("Test test");
	}
	
	
	@Test
	public void findByIdTest() {
		Optional<User> opFoundUser = userRepository.findById(1L);
		User foundUser = opFoundUser.get();
		
		assertThat(foundUser.getName()).isEqualTo("Test test");
	}
	
	@Test
	public void findAllTest() {
		List <User> usersList = userRepository.findAll();
		
		assertThat(usersList.size()).isEqualTo(1);
	}
	
	@Test
	public void loginTest() {
		Optional <User> opLoggedUser = userRepository.login("test@gmail.com", "1234");
		User loggedUser = opLoggedUser.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	
//	@Test
//	public void deleteTest() {
//		Optional<User> opUser = userRepository.findById(1L);
//		userRepository.delete(opUser.get());
//		
//		Optional<User> opUserdelete = userRepository.findById(1L);
//		
//		assertThat(opUserdelete.isPresent()).isEqualTo(false);
//	}
}
