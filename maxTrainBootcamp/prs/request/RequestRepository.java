package com.maxTrainBootcamp.prs.request;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
	Iterable<Request> getRequestByStatusAndUserIdNot(String status, Integer userId);
}
