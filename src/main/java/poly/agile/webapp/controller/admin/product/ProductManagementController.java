package poly.agile.webapp.controller.admin.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import poly.agile.webapp.model.Product;

/*@Controller
@RequestMapping("/admin/product-mgr")*/
public class ProductManagementController {
	
	@GetMapping
	public String products() {
		return "admin/product/list";
	}
	
	@GetMapping("/add")
	public String add() {
		return "admin/product/form";
	}
	
	@PostMapping("/review")
	public String review() {
		return "";
	}
	
	@PostMapping("/save")
	public String save() {
		return "redirect:/admin/add/product";
	}
	
	@PutMapping("/edit{id}")
	public String edit() {
		return "admin/product/edit";
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		return "";
	}
	
	@ModelAttribute("product")
	public Product createProduct() {
		return new Product();
	}
}
