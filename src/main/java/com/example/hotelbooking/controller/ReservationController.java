package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Reservation;
import com.example.hotelbooking.service.ReservationsService;
import com.example.hotelbooking.service.RoomsService;
import com.example.hotelbooking.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
	
	private final ReservationsService reservationsService;
	
	private final RoomsService roomsService;
	
	private final UsersService usersService;
	
	public ReservationController(ReservationsService reservationsService, RoomsService roomsService,
								 UsersService usersService) {
		this.reservationsService = reservationsService;
		this.roomsService = roomsService;
		this.usersService = usersService;
	}
	
	@GetMapping("/{reservation_id}")
	public String getReservation(@PathVariable Long reservation_id, Model model) {
		model.addAttribute("reservations", reservationsService.get(reservation_id));
		return "reservations";
	}
	
	@GetMapping
	public String getReservations(Model model, @ModelAttribute(name = "errorMessage") String errorMessage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		List<Reservation> reservations =
				isAdmin ? reservationsService.getAll() :
				reservationsService.getByUserId(usersService.getByName(auth.getName()).getId());
		model.addAttribute("reservations", reservations);
		if (errorMessage != null) {
			if (!errorMessage.isEmpty()) {
				model.addAttribute("error", errorMessage);
			}
		}
		return "reservations";
	}
	
	@PostMapping("/add")
	public String addReservation(@ModelAttribute(value = "reservation") Reservation reservation) {
		reservationsService.save(reservation);
		return "redirect:/reservations";
	}
	
	@PostMapping("/add/{room_id}")
	public String addReservationWithRoomId(@PathVariable Long room_id,
										   @ModelAttribute(value = "new_reservation") Reservation reservation) {
		reservation.setRoom(roomsService.get(room_id));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		reservation.setUser(usersService.getByName(auth.getName()));
		reservationsService.save(reservation);
		return "redirect:/rooms";
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
