package poly.agile.webapp.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.product.ProductService;

@Controller
@RequestMapping("/products")
public class ProductDetailController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/{id}")
	public String getProductDetail(@PathVariable("id") Integer id, Model model) {
		Product product = service.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("productPage", true);
		return "products/product-detail";
	}
	
}
