package poly.agile.webapp.controller.admin.product;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.model.ProductSpec;
import poly.agile.webapp.model.ProductSpecDetail;
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

	@GetMapping("/clearForm")
	public @ResponseBody boolean clear(SessionStatus status) {
		status.setComplete();
		return true;
	}

	@PostMapping(params = "addSpecRow")
	public String addSpecRow(@ModelAttribute("product") Product product, @RequestParam("addSpecRow") Integer rowIndex) {
		addProductSpecificationRow(product);
		return "admin/products/create";
	}

	@PostMapping(params = "addSpecDetailRow")
	public String addSpecDetailRow(@ModelAttribute("product") Product product,
			@RequestParam("addSpecDetailRow") Integer rowIndex) {
		ProductSpec productSpec = product.getProductSpecs().get(rowIndex.intValue());
		ProductSpecDetail detail = new ProductSpecDetail();
		detail.setProductSpec(productSpec);
		productSpec.getProductSpecDetails().add(detail);
		return "admin/products/create";
	}

	@PostMapping(params = "removeSpecRow")
	public String removeSpecRow(@ModelAttribute("product") Product product,
			@RequestParam("removeSpecRow") Integer rowIndex) {
		System.err.println("Product specification size: " + product.getProductSpecs().size());
		product.getProductSpecs().remove(rowIndex.intValue());
		return "admin/products/create";
	}

	@PostMapping(params = "removeSpecDetailRow")
	public String removeSpecDetailRow(@ModelAttribute("product") Product product,
			@RequestParam("removeSpecDetailRow") String values) {
		String[] rows = values.split(",");

		int specIndex = Integer.parseInt(rows[0]);
		int specDetailIndex = Integer.parseInt(rows[1]);

		ProductSpec productSpec = product.getProductSpecs().get(specIndex);

		productSpec.getProductSpecDetails().remove(specDetailIndex);

		if (productSpec.getProductSpecDetails().isEmpty())
			product.getProductSpecs().remove(specIndex);

		return "admin/products/create";
	}

	@PostMapping(params = "create")
	public String create(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			SessionStatus status) {
		if (bindingResult.hasFieldErrors()) {
			return "admin/products/create";
		}
		try {
			productService.create(product);
			status.setComplete();
			return "redirect:/admin/products";
		} catch (DuplicateFieldException e) {
			bindingResult.rejectValue("name", "product.name", "Trùng tên sản phẩm!");
			return "admin/products/create";
		} catch (Exception e) {
			e.printStackTrace();
			return "admin/products/create";
		}
	}

	@ModelAttribute("product")
	public Product createProduct() {
		return new Product();
	}

	@ModelAttribute("brands")
	public List<Brand> getBrands() {
		return brandService.findAll();
	}

	@ModelAttribute("specifications")
	public List<Specification> getSpecifications() {
		return specService.findAll();
	}

	private void addProductSpecificationRow(Product product) {
		List<ProductSpec> productSpecs = product.getProductSpecs();
		if (productSpecs == null) {
			productSpecs = new ArrayList<>();
		}
		ProductSpec productSpec = new ProductSpec();
		ProductSpecDetail detail = new ProductSpecDetail();
		detail.setProductSpec(productSpec);
		List<ProductSpecDetail> details = new ArrayList<>();
		details.add(detail);
		productSpec.setProductSpecDetails(details);
		productSpec.setProduct(product);
		productSpecs.add(productSpec);

		product.setProductSpecs(productSpecs);
	}

}
