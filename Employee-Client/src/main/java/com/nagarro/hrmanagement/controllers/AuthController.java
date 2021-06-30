package com.nagarro.hrmanagement.controllers;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nagarro.hrmanagement.entities.User;
import com.nagarro.hrmanagement.interfaces.UserInterface;
@Controller
public class AuthController {
	
	@Autowired
	private UserInterface userService;
	
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("user" , new User());
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
	  session.invalidate();
	  return "redirect:/";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signin(@ModelAttribute("user") User user, Model model, HttpSession session) {
		User authuser = userService.login(user);
		if(authuser != null) {
			model.addAttribute("user", true);
			session.setAttribute("userID", authuser.getUserID());
			return "redirect:/home";
		}
		return "redirect:/";
	}
	
}
