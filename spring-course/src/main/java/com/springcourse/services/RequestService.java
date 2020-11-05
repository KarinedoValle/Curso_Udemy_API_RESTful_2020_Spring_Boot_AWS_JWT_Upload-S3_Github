package com.springcourse.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.repositories.RequestRepository;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;

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

	public PageModel<Request> findAllByOwnerIdPageable(Long id, Pageable pageable) {		
		Page<Request> page = requestRepository.findAllByOwnerId(id, pageable);
		
		PageModel<Request> requestPage = new PageModel<>(page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getSort(), page.getContent()); 
		
		return requestPage;
	}

	public PageModel<Request> findAllPageable(Pageable pageable) {
		Page<Request> page = requestRepository.findAll(pageable);
		
		PageModel<Request> requestPage = new PageModel<>(page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getSort(), page.getContent());
		return requestPage;
	}

}
