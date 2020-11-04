package com.springcourse.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.repositories.RequestRepository;
import com.springcourse.exceptions.NotFoundException;

@Service
public class RequestService {
	
	@Autowired 
	RequestRepository requestRepository;
	
	public Request save (Request request) {
		request.setState(RequestState.OPEN);
		request.setCriationDate(new Date());
		
		return requestRepository.save(request);
	}
	
	public Request update (Request request) {
		return requestRepository.save(request);
	}
	
	public Request findById(Long id) throws NotFoundException {
		Optional<Request> opRequest = requestRepository.findById(id);
		
		if(!opRequest.isPresent()) {
			throw new NotFoundException("Não existe um pedido com id " + id + ".");
		}
		
		return opRequest.get();
	}
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public List<Request> findAllByOwnerId(Long id){
		return requestRepository.findAllByOwnerId(id);
	}
	
	public void updateState(Long id, RequestState state) {
		requestRepository.updateState(id, state);
	}

}
