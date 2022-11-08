package cn.edu.njfu.simple.sql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.model.User;
import cn.edu.njfu.simple.sql.service.UserService;
import io.swagger.annotations.ApiOperation;

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
	
	@ApiOperation(value = "根据id查找用户")
	@RequestMapping("/get")
	public CustomResponse<User> getUser(Long id) {
		CustomResponse<User> response = new CustomResponse<User>(0, userService.getUserById(id));
		return response;
	}
}
