package simple.sql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import simple.sql.model.CustomResponse;
import simple.sql.model.User;
import simple.sql.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "列出所有用户")
	@RequestMapping("/list")
	public CustomResponse<List<User>> listAllUsers() {
		CustomResponse<List<User>> response = new CustomResponse<List<User>>(0, userService.listUsers());
		return response;
	}
}
