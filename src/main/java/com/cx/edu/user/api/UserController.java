package com.cx.edu.user.api;

import com.cx.edu.base.model.PageDTO;
import com.cx.edu.config.MyLog;
import com.cx.edu.user.model.LoginCondition;
import com.cx.edu.user.model.LoginDTO;
import com.cx.edu.user.model.RegisterCondition;
import com.cx.edu.user.model.UsersDTO;
import com.cx.edu.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;


	@MyLog(value = "登录")
	@PostMapping("login")
	public LoginDTO login(@RequestBody @Valid LoginCondition loginCondition) {
		return userService.login(loginCondition);
	}

	@PostMapping("logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout() {
		userService.logout();
	}

	@PostMapping("register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void register(@RequestBody @Valid RegisterCondition registerCondition) {
		userService.register(registerCondition);
	}

	@GetMapping("users")
	public PageDTO getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
							@RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize,
							@RequestParam(value = "userName", required = false) String userName,
							@RequestParam(value = "mobile", required = false) String mobile) {
		List<UsersDTO> usersDTOS = userService.getUsers(page, pageSize, userName, mobile);
		return new PageDTO(new PageInfo(usersDTOS), usersDTOS);
	}

	@PutMapping("delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@RequestParam(value = "userId") Long userId) {
		userService.deleteUser(userId);
	}

}
