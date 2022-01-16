package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.service.ImagesService;
import com.example.hotelbooking.service.RoomsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomsService roomsService;

    private final ImagesService imagesService;


    public RoomController(RoomsService roomsService, ImagesService imagesService) {
        this.roomsService = roomsService;
        this.imagesService = imagesService;
    }

    @GetMapping("/{room_id}")
    public String getRoom(@PathVariable Long room_id, Model model) {
        model.addAttribute("rooms", roomsService.get(room_id));
        Room room = new Room();
        model.addAttribute("new_room", room);
        return "rooms";
    }

    @GetMapping
    public String getRooms(Model model, @ModelAttribute(name = "errorMessage") String errorMessage) {
        model.addAttribute("rooms", roomsService.getAll());
        if (errorMessage != null)
            if (!errorMessage.isEmpty())
                model.addAttribute("error", errorMessage);
        Room new_room = new Room();
        model.addAttribute("new_room", new_room);
        return "rooms";
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute(value = "new_room") Room room,
                          @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.getSize() > 0 && !file.getName().isEmpty())
            room.addImage(imagesService.save(file));
        roomsService.save(room);
        return "redirect:/rooms";
    }

    @PostMapping("/edit")
    public String editRoom(@ModelAttribute(value = "room") Room room) {
        roomsService.update(room);
        return "redirect:/rooms";
    }

    @PostMapping("/delete/{room_id}")
    public String deleteRoom(@PathVariable Long room_id) {
        roomsService.remove(room_id);
        return "redirect:/rooms";
    }

    @PostMapping("/{room_id}/addImage")
    public String addRoomImage(@PathVariable Long room_id, @RequestParam(value = "file") MultipartFile file) {
        if (file.getSize() > 0 && !file.getName().isEmpty()) {
            Room room = roomsService.get(room_id);
            room.addImage(imagesService.save(file));
            roomsService.update(room);
        }
        return "redirect:/rooms";
    }

}
