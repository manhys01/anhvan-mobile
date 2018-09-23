package poly.agile.webapp.controller.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.brand.BrandService;

@RestController
@RequestMapping("/admin/brands")
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping
	public List<Brand> all() {
		return service.findAll();
	}

	@PostMapping
	public Brand newBrand(@RequestBody Brand brand) {
		return service.create(brand);
	}
	
	@GetMapping("/{id}")
	public Brand one(@PathVariable Integer id) {
		return service.findById(id);	
	}
	
	@PutMapping("/{id}")
	public Brand replaceBrand(@RequestBody Brand brand, @PathVariable Integer id) {
		return service.update(brand);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteBrand(@PathVariable Integer id) {
		return service.delete(service.findById(id));
	}

	/*@ModelAttribute("brands")
	public List<Brand> all() {
		return service.findAll();
	}*/

}
