package com.springcourse.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	
	
	public List<Request> findAllByOwnerId(Long id);
	
	@Query("UPDATE request SET state = :state WHERE id = :id")
	public Request updateState(Long id, RequestState state);

}
