package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

public class UserUpdateDto {
	
	@NotNull @NotBlank
	private String name;
	
	@NotNull @NotBlank
	@Email(message = "E-mail inválido.")
	private String email;
	
	@NotNull @NotBlank
	@Size(max=99, message = "O comprimento da senha deve ser até 100.")
	private String password;
	

	private List<Request> requests = new ArrayList<Request>();
	
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public UserUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserUpdateDto(@NotNull @NotBlank String name,
			@NotNull @NotBlank @Email(message = "E-mail inválido.") String email,
			@NotNull @NotBlank @Size(max = 99, message = "O comprimento da senha deve ser até 100.") String password,
			List<Request> requests, List<RequestStage> stages) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.requests = requests;
		this.stages = stages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<RequestStage> getStages() {
		return stages;
	}

	public void setStages(List<RequestStage> stages) {
		this.stages = stages;
	}
	
	public User TransformToUser() {
		User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
		
		return user;
	}

}
