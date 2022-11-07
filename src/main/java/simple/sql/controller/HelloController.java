package simple.sql.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@ApiOperation(value = "说声hi")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		logger.info("hello world from Hello World Controller");
		return "hello world";
	}
}
