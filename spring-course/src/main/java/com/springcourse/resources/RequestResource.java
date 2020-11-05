package com.springcourse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.services.RequestService;
import com.springcourse.services.RequestStageService;

@RestController
@RequestMapping("/requests")
public class RequestResource {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request){
		Request createdRequest = requestService.save(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@RequestBody Request request, @PathVariable Long id){
		request.setId(id);
		Request updatedRequest = requestService.update(request);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> findById(@PathVariable Long id) throws NotFoundException{
		Request foundRequest = requestService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(foundRequest);
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> findAll(){
		List<Request> requestsList = requestService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(requestsList);
	}
	
	@GetMapping("/page")
	public ResponseEntity<PageModel<Request>> findAllPageable(@PageableDefault(size = 6, page = 0) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		PageModel<Request> requestsList = requestService.findAllPageable(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestsList);
	}
	
	
	@GetMapping("/stagesByRequest/{id}")
	public ResponseEntity<List<RequestStage>> findAllByRequest(@PathVariable Long id){
		List<RequestStage> requestStagesList = requestStageService.findAllByRequestId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestStagesList);
	}
	
	@GetMapping("/page/stagesByRequest/{id}")
	public ResponseEntity<PageModel<RequestStage>> findAllByRequestPageable(@PathVariable Long id, @PageableDefault(size = 6, page = 0) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		PageModel<RequestStage> requestStagesList = requestStageService.findAllByRequestIdPageable(id, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestStagesList);
	}
	

}
