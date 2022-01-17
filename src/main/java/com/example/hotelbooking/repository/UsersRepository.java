package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByName(String username);
}
