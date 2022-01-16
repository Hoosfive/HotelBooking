package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomsService roomsService;

    public RoomController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping("/{room_id}")
    public Room getRoom(@PathVariable Long room_id) {
        return roomsService.get(room_id);
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomsService.getAll();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomsService.save(room);
    }

    @PutMapping
    public Room editRoom(@RequestBody Room room) {
        return roomsService.update(room);
    }

    @DeleteMapping("/{room_id}")
    public void deleteRoom(@PathVariable Long room_id) {
        roomsService.remove(room_id);
    }
}
