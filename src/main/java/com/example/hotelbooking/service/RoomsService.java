package com.example.hotelbooking.service;


import com.example.hotelbooking.ObjectNotFoundException;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.repository.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {
	
	private final RoomsRepository roomsRepository;
	
	public RoomsService(RoomsRepository roomsRepository) {
		this.roomsRepository = roomsRepository;
	}
	
	public Room get(Long id) {
		return roomsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Room.class, id));
	}
	
	public List<Room> getAll() {
		return (List<Room>) roomsRepository.findAll();
	}
	
	public void save(Room room) {
		roomsRepository.save(room);
	}
	
	public void update(Room room) {
		roomsRepository.save(room);
	}
	
	public void remove(Long id) {
		roomsRepository.deleteById(id);
	}
	
	
}
