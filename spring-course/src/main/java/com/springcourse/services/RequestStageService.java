package com.springcourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.repositories.RequestStageRepository;

@Service
public class RequestStageService {
	
	@Autowired 
	RequestStageRepository requestStageRepository;
	
	public RequestStage save(RequestStage requestStage) {
		return requestStageRepository.save(requestStage);
	}
	
	public RequestStage update(RequestStage requestStage) {
		return requestStageRepository.save(requestStage);
	}
	
	public RequestStage findById(Long id) {
		Optional<RequestStage> opRequestStage = requestStageRepository.findById(id);
		return opRequestStage.get();
	}
	
	public List<RequestStage> findAll(){
		return requestStageRepository.findAll();
	}
	
	public List<RequestStage> findAllByRequest(Request request){
		return requestStageRepository.findAllByRequest(request);
	}

}
