package poly.agile.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	@GetMapping("/about")
	public String goAboutPage() {
		return "about";
	}
}
