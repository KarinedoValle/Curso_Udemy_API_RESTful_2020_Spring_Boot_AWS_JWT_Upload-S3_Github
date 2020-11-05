package com.springcourse.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.repositories.RequestStageRepository;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;

@Service
public class RequestStageService {
	
	@Autowired 
	RequestStageRepository requestStageRepository;
	
	public RequestStage save(RequestStage requestStage) {
		requestStage.setRealizationDate(new Date());
		return requestStageRepository.save(requestStage);
	}
	
	public RequestStage update(RequestStage requestStage) {
		return requestStageRepository.save(requestStage);
	}
	
	public RequestStage findById(Long id) throws NotFoundException {
		Optional<RequestStage> opRequestStage = requestStageRepository.findById(id);
		
		if(!opRequestStage.isPresent()) {
			throw new NotFoundException("Não existe um estágio com id " + id + ".");
		}
		return opRequestStage.get();
	}
	
	public List<RequestStage> findAll(){
		return requestStageRepository.findAll();
	}
	
	public List<RequestStage> findAllByRequestId(Long id){
		return requestStageRepository.findAllByRequestId(id);
	}

	public PageModel<RequestStage> findAllByRequestIdPageable(Long id, Pageable pageable) {
		Page<RequestStage> page = requestStageRepository.findAllByRequestId(id, pageable);
		
		PageModel<RequestStage> requestStagePage = new PageModel<>(page.getTotalElements(),page.getSize(),page.getTotalPages(), page.getSort(), page.getContent());
		return requestStagePage;
	}

	public PageModel<RequestStage> findAllPageable(Pageable pageable) {
		Page<RequestStage> page = requestStageRepository.findAll(pageable);
		
		PageModel<RequestStage> requestStagePage = new PageModel<>(page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getSort(), page.getContent());
		return requestStagePage;
	}

}
