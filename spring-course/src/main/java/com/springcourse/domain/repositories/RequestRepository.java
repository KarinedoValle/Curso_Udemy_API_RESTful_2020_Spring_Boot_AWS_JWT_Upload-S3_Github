package com.springcourse.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	
	
	public List<Request> findAllByOwnerId(Long id);
	
	public Page<Request> findAllByOwnerId(Long id, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE request SET state = :state WHERE id = :id")
	public int updateState(Long id, RequestState state);

}
