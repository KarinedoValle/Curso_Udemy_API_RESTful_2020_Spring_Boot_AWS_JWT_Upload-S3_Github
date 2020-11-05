package com.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.springcourse.domain.enums.Role;

public class UserUpdateRoleDto {
	
	@NotNull(message = "A role deve ser preenchida.")
	private Role role;

	public UserUpdateRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserUpdateRoleDto(Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
