package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends CrudRepository<Reservation, Long> {
}
