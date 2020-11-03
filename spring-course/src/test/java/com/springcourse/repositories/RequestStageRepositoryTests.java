package com.springcourse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.repositories.RequestStageRepository;

@SpringBootTest
public class RequestStageRepositoryTests {

	@Autowired
	private RequestStageRepository requestStageRepository;

	@Test
	public void saveTest() {
		User owner = new User();
		owner.setId(14L);

		Request request = new Request();
		request.setId(5L);
		
		RequestStage requestStage = new RequestStage(null, "Test", new Date(), RequestState.OPEN, request, owner);
		RequestStage createdRequestStage = requestStageRepository.save(requestStage);

		assertThat(createdRequestStage.getId()).isEqualTo(1L);
	}

	@Test
	public void updateTest() {
		Optional<RequestStage> opFoundRequestStage = requestStageRepository.findById(1L);
		RequestStage foundRequestStage = opFoundRequestStage.get();

		foundRequestStage.setDescription("Test test");

		RequestStage updatedRequestStage = requestStageRepository.save(foundRequestStage);

		assertThat(updatedRequestStage.getDescription()).isEqualTo("Test test");
	}

	@Test
	public void findByIdTest() {
		Optional<RequestStage> opFoundRequestStage = requestStageRepository.findById(1L);
		RequestStage foundRequestStage = opFoundRequestStage.get();

		assertThat(foundRequestStage.getDescription()).isEqualTo("Test test2");
	}

	@Test
	public void findAllTest() {
		List<RequestStage> requestStagesList = requestStageRepository.findAll();

		assertThat(requestStagesList.size()).isEqualTo(1);
	}



//	@Test
//	public void deleteTest() {
//		Optional<RequestStage> opRequestStage = requestStageRepository.findById(1L);
//		requestStageRepository.delete(opRequestStage.get());
//		
//		Optional<RequestStage> opRequestStagedelete = requestStageRepository.findById(1L);
//		
//		assertThat(opRequestStagedelete.isPresent()).isEqualTo(false);
//	}

}
