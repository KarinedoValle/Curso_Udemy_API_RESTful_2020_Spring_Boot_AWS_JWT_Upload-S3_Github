package com.springcourse.model;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageRequestModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int page;
	
	private int size;
	
	private Direction direction;
	
	private String sortParam;

	public PageRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageRequestModel(int page, int size, Direction direction, String sortParam) {
		super();
		this.page = page;
		this.size = size;
		this.direction = direction;
		this.sortParam = sortParam;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getSortParam() {
		return sortParam;
	}

	public void setSortParam(String sortParam) {
		this.sortParam = sortParam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
