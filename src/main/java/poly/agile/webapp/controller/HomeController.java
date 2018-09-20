package poly.agile.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("varone", "var one");
		model.addAttribute("vartwo", "var two");
		model.addAttribute("varthree", "var three");
		model.addAttribute("num", 2);
		return "index";
	}
}
