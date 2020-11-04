package com.springcourse.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;

public class PageModel<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long totalElements;
	
	private int pageSize;
	
	private int totalPages;
	
	private Sort sort;
	
	private List<E> elements;

	public PageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageModel(Long totalElements, int pageSize, int totalPages, Sort sort, List<E> elements) {
		super();
		this.totalElements = totalElements;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.sort = sort;
		this.elements = elements;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public List<E> getElements() {
		return elements;
	}

	public void setElements(List<E> elements) {
		this.elements = elements;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
