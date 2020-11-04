package com.springcourse.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ApiError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String message;
	
	private Date date;

	public ApiError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiError(int code, String message, Date date) {
		super();
		this.code = code;
		this.message = message;
		this.date = date;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
