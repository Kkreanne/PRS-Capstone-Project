package com.maxTrainBootcamp.prs.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> { //Integer is the parameter object type

	User findByUsernameAndPassword(String username, String password);
}
