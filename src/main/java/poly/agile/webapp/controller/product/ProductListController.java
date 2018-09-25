package poly.agile.webapp.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
		Page<ProductDTO> list = service.list(page);
		List<Integer> pages = new ArrayList<>();
		for (int i = 1; i <= list.getTotalPages(); i++) {
			pages.add(i);
		}
		model.addAttribute("products", list.getContent());
		model.addAttribute("productPage", true);
		model.addAttribute("pages", pages);
		return "products/list";
	}
	
}
