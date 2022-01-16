package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Reservation;
import com.example.hotelbooking.service.ImagesService;
import com.example.hotelbooking.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class ReservationController {

    @Autowired
    ReservationsService reservationsService;

    @Autowired
    ImagesService imagesService;

    public ReservationController(ReservationsService reservationsService, ImagesService imagesService) {
        this.reservationsService = reservationsService;
        this.imagesService = imagesService;
    }

    @GetMapping("/{reservation_id}")
    public Reservation getReservation(@PathVariable Long reservation_id) {
        return reservationsService.get(reservation_id);
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationsService.getAll();
    }

    @PostMapping
    public Reservation reserveRoom(@RequestBody Reservation reservation) {
        return reservationsService.save(reservation);
    }

    @PutMapping
    public Reservation editReservation(@RequestBody Reservation reservation) {
        return reservationsService.update(reservation);
    }

    @DeleteMapping("/{reservation_id}")
    public void deleteReservation(@PathVariable Long reservation_id) {
        reservationsService.remove(reservation_id);
    }
}
