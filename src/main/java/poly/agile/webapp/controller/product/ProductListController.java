package poly.agile.webapp.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.ProductService;


@Controller
public class ProductListController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public String showTest(Model model) {
		List<Product> list = service.findAll();
		model.addAttribute("products", list);
		return "products/index";
	}
}
