package poly.agile.webapp.controller.admin.brand;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.brand.BrandService;

@Controller
@RequestMapping("/admin")
public class BrandController {

	@Autowired
	private BrandService service;

	@GetMapping("/brands")
	public String all(Model model) {
		model.addAttribute("brands", service.findAll());
		return "admin/brands/list";
	}

	@GetMapping("/brand")
	public String add() {
		return "admin/brands/add";
	}

	@GetMapping("/brand/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("brand", service.findById(id));
		return "admin/brands/edit";
	}

	@PostMapping("/brand")
	public String save(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {
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

	@PutMapping("/brand/{id}")
	public String replace(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {
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

	@DeleteMapping("/brand/{id}")
	public @ResponseBody boolean delete(@PathVariable Integer id) {
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
