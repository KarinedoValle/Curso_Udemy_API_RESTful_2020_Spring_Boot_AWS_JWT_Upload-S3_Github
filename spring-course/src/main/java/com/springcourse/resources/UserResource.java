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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.dto.UserUpdateRoleDto;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.services.RequestService;
import com.springcourse.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) throws NotFoundException{
		User foundUser = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(foundUser);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> usersList = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}
	
	@GetMapping("/page")
	public ResponseEntity<PageModel<User>> findAllPageable(@PageableDefault(size = 6, page = 0) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		PageModel<User> usersList = userService.findAllPageable(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto login) throws NotFoundException{
		User loggedUser = userService.login(login.getEmail(), login.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
	}
	
	@GetMapping("/requestsByUser/{id}")
	public ResponseEntity<List<Request>> findAllByOwnerId(@PathVariable Long id){
		List<Request> requestsList = requestService.findAllByOwnerId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestsList);
	}
	
	@GetMapping("/page/requestsByUser/{id}")
	public ResponseEntity<PageModel<Request>> findAllByOwnerIdPageable(@PathVariable Long id, @PageableDefault(size = 6, page = 0) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		
		PageModel<Request> requestsList = requestService.findAllByOwnerIdPageable(id, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(requestsList);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@RequestBody UserUpdateRoleDto userDto, @PathVariable Long id){
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		
		userService.updateRole(user.getId(), user.getRole());
		
		return ResponseEntity.ok().build();
	}

}
