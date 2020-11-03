package com.springcourse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.repositories.RequestRepository;

@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired
	private RequestRepository requestRepository;
	
	
	@Test
	public void saveTest () {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Test", "Test description", new Date(), RequestState.OPEN, owner, null);
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}

	@Test
	public void updateTest() {
		Optional<Request> opFoundRequest = requestRepository.findById(1L);
		Request foundRequest = opFoundRequest.get();
		
		foundRequest.setSubject("Test test");
		
		Request updatedRequest = requestRepository.save(foundRequest);
		
		assertThat(updatedRequest.getSubject()).isEqualTo("Test test");
	}
	
	
	@Test
	public void findByIdTest() {
		Optional<Request> opFoundRequest = requestRepository.findById(1L);
		Request foundRequest = opFoundRequest.get();
		
		assertThat(foundRequest.getSubject()).isEqualTo("Test test");
	}
	
	@Test
	public void findAllTest() {
		List <Request> requestsList = requestRepository.findAll();
		
		assertThat(requestsList.size()).isEqualTo(1);
	}
	
	@Test
	public void findAllByOwnerIdTest() {
		List <Request> requestsList = requestRepository.findAllByOwnerId(1L);
		
		assertThat(requestsList.size()).isEqualTo(1);
	}
	
	@Test
	public void updateStateTest() {
		int affectedLines = requestRepository.updateState(1l, RequestState.IN_PROGRESS);
		
		assertThat(affectedLines).isEqualTo(1);
		
	}
	
	
//	@Test
//	public void deleteTest() {
//		Optional<Request> opRequest = requestRepository.findById(1L);
//		requestRepository.delete(opRequest.get());
//		
//		Optional<Request> opRequestdelete = requestRepository.findById(1L);
//		
//		assertThat(opRequestdelete.isPresent()).isEqualTo(false);
//	}

}
