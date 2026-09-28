package vn.iotstar.custom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomAuthController {

	@GetMapping("/custom/login")
	public String login() {
		return "custom/auth/login";
	}

}
