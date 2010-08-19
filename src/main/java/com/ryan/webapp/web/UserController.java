package com.ryan.webapp.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ryan.webapp.model.User;
import com.ryan.webapp.service.UserService;

@Controller
@RequestMapping("/user.htm")
public class UserController {
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String showUserInputForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processUserForm(@Valid User user,BindingResult result, Map<String, Object> model) {
		if(result.hasErrors()) {  
			return "form";  
		}  
		userService.addUser(user);
		model.put("user", user);
		return "redirect:result";
	}

	@Autowired(required=true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
