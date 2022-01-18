package com.example.hotelbooking.service;

import com.example.hotelbooking.exception.ObjectNotFoundException;
import com.example.hotelbooking.entity.Reservation;
import com.example.hotelbooking.repository.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationsService {
	
	private final ReservationsRepository reservationsRepository;
	
	public ReservationsService(ReservationsRepository reservationsRepository) {
		this.reservationsRepository = reservationsRepository;
	}
	
	public Reservation get(Long id) {
		return reservationsRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(Reservation.class, id));
	}
	
	public List<Reservation> getAll() {
		return (List<Reservation>) reservationsRepository.findAll();
	}
	
	public void save(Reservation reservation) {
		reservationsRepository.save(reservation);
	}
	
	public void update(Reservation reservation) {
		reservationsRepository.save(reservation);
	}
	
	public void remove(Long id) {
		reservationsRepository.deleteById(id);
	}
	
	public List<Reservation> getByUserId(Long id) {
		return reservationsRepository.findReservationsByUserId(id);
	}
	
}
