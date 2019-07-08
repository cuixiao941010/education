package com.cx.edu.user.api;

import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;
import com.cx.edu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("login")
	public LoginDTO login(@RequestBody @Valid LoginCondition loginCondition) {
		return userService.login(loginCondition);
	}

	@PostMapping("logout")
	public void logout() {
		userService.logout();
	}
}
