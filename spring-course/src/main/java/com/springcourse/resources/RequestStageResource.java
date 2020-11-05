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

import com.springcourse.domain.RequestStage;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.services.RequestStageService;

@RestController
@RequestMapping("/requestStages")
public class RequestStageResource {
	
	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage requestStage){
		RequestStage createdRequestStage = requestStageService.save(requestStage);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RequestStage> update(@RequestBody RequestStage requestStage, @PathVariable Long id){
		requestStage.setId(id);
		RequestStage updatedRequestStage = requestStageService.update(requestStage);

		return ResponseEntity.status(HttpStatus.OK).body(updatedRequestStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> findById(@PathVariable Long id) throws NotFoundException{
		RequestStage foundRequestStage = requestStageService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(foundRequestStage);
	}
	
	@GetMapping
	public ResponseEntity<List<RequestStage>> findAll(){
		List<RequestStage> requestStagesList = requestStageService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(requestStagesList);
	}
	
	@GetMapping("/page")
	public ResponseEntity<PageModel<RequestStage>> findAllPageable(@PageableDefault(size = 6, page = 0) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		PageModel<RequestStage> requestStagesList = requestStageService.findAllPageable(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestStagesList);
	}
	
	

}
