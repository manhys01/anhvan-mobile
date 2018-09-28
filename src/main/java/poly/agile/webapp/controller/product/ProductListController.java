package poly.agile.webapp.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.service.product.ProductService;
import poly.agile.webapp.util.pagination.Pagination;

@Controller
public class ProductListController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public String show(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<ProductDTO> pages = service.getPages(page);
		Pagination pagination = new Pagination(pages.getTotalPages(), 3, page);

		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("productPage", true);
		return "products/list";
	}

	@GetMapping(value = "/products/search", params = "query")
	public String search(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("query") String keyword) {
		Page<ProductDTO> pages = service.search(keyword, page);
		Pagination pagination = new Pagination(pages.getTotalPages(), 3, page);

		pages.getContent().forEach(e->{
			System.out.println(e.getName());
		});
		
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("productPage", true);
		return "products/search";
	}

}
