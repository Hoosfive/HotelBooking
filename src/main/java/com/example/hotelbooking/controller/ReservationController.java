package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Reservation;
import com.example.hotelbooking.service.ReservationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationsService reservationsService;

    public ReservationController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;

    }

    @GetMapping("/{reservation_id}")
    public String getReservation(@PathVariable Long reservation_id, Model model) {
        model.addAttribute("reservations", reservationsService.get(reservation_id));
        return "reservations";
    }

    @GetMapping
    public String getReservations(Model model, @ModelAttribute(name = "errorMessage") String errorMessage) {
        model.addAttribute("reservations", reservationsService.getAll());
        if (errorMessage != null)
            if (!errorMessage.isEmpty())
                model.addAttribute("error", errorMessage);
        return "reservations";
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute(value = "reservation") Reservation reservation) {
        reservationsService.save(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/edit")
    public String editReservation(@ModelAttribute(value = "reservation") Reservation reservation) {
        reservationsService.update(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/delete/{reservation_id}")
    public String deleteReservation(@PathVariable Long reservation_id) {
        reservationsService.remove(reservation_id);
        return "redirect:/reservations";
    }
}
