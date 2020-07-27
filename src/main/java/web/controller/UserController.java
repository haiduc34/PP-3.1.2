package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping (value = "admin")
	public String adminPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", userService.listUsers());
		model.addAttribute("loggedInUser", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		return  "admin/admin";
	}

	@PostMapping(value = "/admin/add")
	public String addUser(@RequestParam("username") String name, @RequestParam("password") String password, @RequestParam("roles") String roles){
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(userService.getRoleByName(roles));
		userService.addUser(new User(name, roleSet, password));
		return "redirect:/admin";
	}

	@PostMapping("/admin/remove")
	public String removeUser(@RequestParam("id") Long id, Model model){

		userService.removeUser(id);

		return "redirect:/admin";
	}

	@PostMapping("/admin/update")
	public String userData(Model model, @RequestParam("id") Long id, @RequestParam("username")
			String name, @RequestParam("password") String password, @RequestParam("roles") String role){
		HashSet<Role> roles = new HashSet<>();
		roles.add(userService.getRoleByName(role));
		userService.updateUser(new User(id, name, roles, password));
		return "redirect:/admin";
	}

	@GetMapping("/user")
	public String userPage(Model model){

		model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "user/userpage";
	}

	@PostMapping("/logout")
	public String logoutPage () {
		return "redirect:/admin";
	}



}