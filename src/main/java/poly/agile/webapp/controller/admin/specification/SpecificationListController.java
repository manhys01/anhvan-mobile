package poly.agile.webapp.controller.admin.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.agile.webapp.service.specification.SpecificationSerivce;

@Controller
@RequestMapping("/admin/product/specifications")
public class SpecificationListController {

	@Autowired
	private SpecificationSerivce specificationSerivce;

	@GetMapping
	public String all() {
		specificationSerivce.findAll();
		return "admin/specifications/list";
	}

	@ModelAttribute("adminProductPage")
	public boolean active() {
		return true;
	}

}
