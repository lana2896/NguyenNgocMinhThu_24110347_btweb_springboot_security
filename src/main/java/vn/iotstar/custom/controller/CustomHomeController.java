package vn.iotstar.custom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomHomeController {

	@GetMapping({ "/custom", "/custom/" })
	public String home() {
		return "custom/home";
	}

}
