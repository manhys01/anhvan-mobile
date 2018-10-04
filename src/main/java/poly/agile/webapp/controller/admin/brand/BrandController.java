package poly.agile.webapp.controller.admin.brand;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.brand.BrandService;

@Controller
@RequestMapping("/admin")
public class BrandController {

	@Autowired
	private BrandService service;
	
	@Autowired
    protected Validator validator;

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
	public String save(@ModelAttribute("brand") Brand brand, @RequestParam("image") MultipartFile image,
			Errors errors, SessionStatus status) {
		
		validator.validate(brand, errors);
		
		if (errors.hasErrors()) {
			return "admin/brands/add";
		}

		if (!image.isEmpty()) {
			try (InputStream in = image.getInputStream()) {
				Path target = Paths.get("src/main/resources/static/images/brands/" + brand.getName() + ".png");
				Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
				brand.setLogo("/images/brands/" + brand.getName() + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			service.create(brand);
		} catch (DuplicateFieldException e) {
			e.printStackTrace();
			errors.rejectValue("name", "brand.name", "Trùng tên thương hiệu!");
			return "admin/brands/add";
		}

		status.setComplete();
		return "redirect:/admin/brands";
	}

	@PutMapping("/brand/{id}")
	public String replace(@ModelAttribute("brand") Brand brand, @RequestParam("image") MultipartFile image,
			Errors errors, SessionStatus status) {
		
		validator.validate(brand, errors);
		
		if (errors.hasErrors()) {
			return "admin/brands/edit";
		}

		if (!image.isEmpty()) {
			try (InputStream in = image.getInputStream()) {
				Path target = Paths.get("src/main/resources/static/images/brands/" + brand.getName() + ".png");
				Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
				brand.setLogo("/images/brands/" + brand.getName() + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			service.update(brand);
		} catch (DuplicateFieldException e) {
			e.printStackTrace();
			errors.rejectValue("name", "brand.name", "Trùng tên thương hiệu!");
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
