package poly.agile.webapp.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.service.product.ProductService;

@Controller
public class ProductListController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public String show(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		List<ProductDTO> list = service.list(page);
		long totalPages = service.totalPages();
		List<Integer> pages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			pages.add(i);
		}
		model.addAttribute("products", list);
		model.addAttribute("productPage", true);
		model.addAttribute("pages", pages);
		return "products/list";
	}
	
}
