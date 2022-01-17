package com.example.hotelbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String rootPath() {
		return "redirect:/rooms";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/errorsNotificator")
	public ModelAndView errorsHandler(@RequestParam() String error, @RequestParam() String referer,
									  RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:" + referer);
		String errorMessage = "";
		if (error.equals("accessDenied")) {
			errorMessage = "У вас нет прав для выполнения этого действия";
		}
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		return modelAndView;
	}
	
	
}
