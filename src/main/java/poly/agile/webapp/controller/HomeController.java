package poly.agile.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.agile.webapp.service.product.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("homePage", true);
		model.addAttribute("top5ProductNewest", productService.findTop5ProductNewest());
		model.addAttribute("top4ProductMostSell", productService.findTop4ProductMostSell());
		model.addAttribute("top4ProductMostView", productService.findTop4ProductMostView());
		return "index";
	}

}
