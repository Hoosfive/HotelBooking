package com.example.hotelbooking.service;

import com.example.hotelbooking.ObjectNotFoundException;
import com.example.hotelbooking.entity.Reservation;
import com.example.hotelbooking.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationsService {

    @Autowired
    ReservationsRepository reservationsRepository;

    public ReservationsService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    public Reservation get(Long id) {
        return reservationsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Reservation.class, id));
    }

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationsRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationsRepository.save(reservation);
    }

    public Reservation update(Reservation reservation) {
        return reservationsRepository.save(reservation);
    }

    public void remove(Long id) {
        reservationsRepository.deleteById(id);
    }

}
