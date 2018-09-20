package poly.agile.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	
	@GetMapping("/contact")
	public String goContactPage() {
		return "contact";
	}
}
