package com.maxTrainBootcamp.prs.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> { //Based on the User Entity or table in the db, Integer is the primary key object type for id

	User findByUsernameAndPassword(String username, String password);
}
