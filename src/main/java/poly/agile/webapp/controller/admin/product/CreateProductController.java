package poly.agile.webapp.controller.admin.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.model.ProductSpec;
import poly.agile.webapp.model.Specification;
import poly.agile.webapp.service.brand.BrandService;
import poly.agile.webapp.service.product.ProductService;
import poly.agile.webapp.service.specification.SpecificationSerivce;

@Controller
@RequestMapping(value = { "/admin/products/create" })
@SessionAttributes(names = { "brands", "specifications", "product" })
public class CreateProductController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private SpecificationSerivce specService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public String create() {
		return "admin/products/create";
	}

	@PostMapping(params = "addRow")
	public String addRowAddForm(@ModelAttribute("product") Product product, @RequestParam("addRow") Integer rowIndex) {
		List<ProductSpec> list = product.getProductSpecs();
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(new ProductSpec());
		product.setProductSpecs(list);
		return "admin/products/create";
	}

	@PostMapping(params = "removeRow")
	public String removeRowAddForm(@ModelAttribute("product") Product product,
			@RequestParam("removeRow") Integer rowIndex) {
		product.getProductSpecs().remove(rowIndex.intValue());
		return "admin/products/add";
	}

	@PostMapping(params = "create")
	public String create(@ModelAttribute("product") Product product, SessionStatus status) {
		try {
			productService.create(product);
			status.setComplete();
		} catch (DuplicateFieldException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/products";
	}

	@ModelAttribute("brands")
	public List<Brand> getBrands() {
		return brandService.findAll();
	}

	@ModelAttribute("specifications")
	public List<Specification> getSpecifications() {
		return specService.findAll();
	}

}
