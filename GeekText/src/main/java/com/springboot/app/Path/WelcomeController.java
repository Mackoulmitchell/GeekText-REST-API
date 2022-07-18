package com.springboot.app.Path;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping("/welcome")
	public String welcome() {
		return "Test Successful.";
	}
}
