package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Integer> {
}
