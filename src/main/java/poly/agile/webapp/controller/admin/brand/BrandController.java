package poly.agile.webapp.controller.admin.brand;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.brand.BrandService;

@Controller
@RequestMapping("/admin/brands")
@SessionAttributes(value = { "brand" })
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping
	public String all(Model model) {
		model.addAttribute("brands", service.findAll());
		return "admin/brands/list";
	}

	@GetMapping("/add")
	public String addBrand() {
		return "admin/brands/add";
	}

	@GetMapping("/edit/{id}")
	public String editBrand(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("brand", service.findById(id));
		return "admin/brands/edit";
	}

	@PostMapping("/save")
	public String saveBrand(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {
		if (errors.hasErrors()) {
			return "admin/brands/add";
		}
		try {
			Brand created = service.create(brand);
			if (created == null) {
				errors.reject("404");
				return "admin/brands/add";
			}
		} catch (DuplicateFieldException e) {
			e.printStackTrace();
		} 
		status.setComplete();
		return "redirect:/admin/brands";
	}

	@PostMapping("/update")
	public String replaceBrand(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {
		if (errors.hasErrors()) {
			return "admin/brands/edit";
		}
		Brand created = service.update(brand);
		if (created == null) {
			errors.reject("404");
			return "admin/brands/edit";
		}
		status.setComplete();
		return "redirect:/admin/brands";
	}

	@PostMapping("/remove/{id}")
	public @ResponseBody boolean removeBrand(@PathVariable Integer id) {
		return service.remove(service.findById(id));
	}

	@ModelAttribute("brand")
	public Brand getBrand() {
		return new Brand();
	}
	
	@ModelAttribute("adminProductPage")
	public boolean active() {
		return true;
	}
}
