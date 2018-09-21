package poly.agile.webapp.controller.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.brand.BrandService;

@Controller
@RequestMapping("/admin/brands")
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping
	public String list() {
		return "admin/brands/list";
	}

	@PostMapping(value = "/save", params = "brandName")
	public Brand create(@RequestParam("brandName") String brandName) {
		return service.create(new Brand(brandName));
	}

	@PostMapping(value = "/save", params = { "brandId", "brandName" })
	public Brand update(@RequestParam("brandId") Integer brandId, @RequestParam("brandName") String brandName) {
		Brand brand = service.findById(brandId);
		if (brand != null) {
			brand.setName(brandName);
			return service.update(brand);
		}
		return null;
	}

	@ModelAttribute("brands")
	public List<Brand> all() {
		return service.findAll();
	}

}
