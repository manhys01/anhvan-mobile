package poly.agile.webapp.controller.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.brand.BrandService;
import poly.agile.webapp.service.product.ProductService;
import poly.agile.webapp.util.pagination.Pagination;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<ProductDTO> pages = productService.getPages(page);
		Pagination pagination = new Pagination(pages.getTotalPages(), 10, page);
		
		System.out.println(pagination);
		
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", pagination);
		
		return "admin/products/list";
	}

	@GetMapping("/add")
	public String add() {
		return "admin/products/add";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("product") Product product) {
		try {
			productService.create(product);
		} catch (DuplicateFieldException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/products/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "admin/products/edit";
	}

	@PostMapping(value = "/delete/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		return productService.remove(productService.findById(id));
	}

	@ModelAttribute("product")
	public Product createProduct() {
		return new Product();
	}

	@ModelAttribute("brands")
	public List<Brand> getBrands() {
		return brandService.findAll();
	}

}
