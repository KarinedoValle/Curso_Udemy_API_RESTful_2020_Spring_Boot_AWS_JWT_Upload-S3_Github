package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.springcourse.domain.enums.Role;


@Entity(name = "user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotBlank
	@Column(length= 75, nullable=false)
	private String name;
	
	@NotNull @NotBlank
	@Email(message = "E-mail inválido.")
	@Column(length= 75, nullable=false, unique=true)
	private String email;
	
	@NotNull @NotBlank
	@Size(max=99, message = "O comprimento da senha deve ser até 100.")
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length= 100, nullable=false)
	private String password;
	
	@NotNull @NotBlank
	@Column(length= 20, nullable=false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@JsonIgnore
	@OneToMany(mappedBy="owner")
	private List<Request> requests = new ArrayList<Request>();
	
	@JsonIgnore
	@OneToMany(mappedBy="owner")
	private List<RequestStage> stages = new ArrayList<RequestStage>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, String email, String password, Role role, List<Request> requests,
			List<RequestStage> stages) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.requests = requests;
		this.stages = stages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
