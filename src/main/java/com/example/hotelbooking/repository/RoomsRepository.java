package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends CrudRepository<Room, Long> {
}
