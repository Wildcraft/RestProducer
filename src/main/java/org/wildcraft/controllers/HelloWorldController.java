package org.wildcraft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@RequestMapping("/sayHello")
	public @ResponseBody String sayHello() {
		return "Hello World!!!, My name is \"REST Producer\"";
	}
}
