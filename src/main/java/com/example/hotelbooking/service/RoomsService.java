package com.example.hotelbooking.service;


import com.example.hotelbooking.ObjectNotFoundException;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomsService {

    @Autowired
    RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public Room get(Long id) {
        return roomsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Room.class, id));
    }

    public List<Room> getAll() {
        return (List<Room>) roomsRepository.findAll();
    }

    public Room save(Room room) {
        return roomsRepository.save(room);
    }

    public Room update(Room room) {
        return roomsRepository.save(room);
    }

    public void remove(Long id) {
        roomsRepository.deleteById(id);
    }


}
