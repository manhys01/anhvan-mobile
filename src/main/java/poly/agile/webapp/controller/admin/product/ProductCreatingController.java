package poly.agile.webapp.controller.admin.product;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.model.ProductSpec;
import poly.agile.webapp.model.ProductSpecDetail;
import poly.agile.webapp.model.Specification;
import poly.agile.webapp.service.brand.BrandService;
import poly.agile.webapp.service.product.ProductService;
import poly.agile.webapp.service.specification.SpecificationSerivce;
import poly.agile.webapp.util.StringUtils;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes(names = { "brands", "specifications", "product" })
public class ProductCreatingController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private SpecificationSerivce specService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private Validator validator;

	@GetMapping
	public String create() {
		return "admin/products/add";
	}

	@GetMapping("/clearForm")
	public @ResponseBody boolean clear(SessionStatus status) {
		status.setComplete();
		return true;
	}

	@PostMapping(params = "addSpecRow")
	public String addSpecRow(@ModelAttribute("product") Product product, @RequestParam("addSpecRow") Integer rowIndex) {
		addProductSpecificationRow(product);
		return "admin/products/add";
	}

	@PostMapping(params = "addSpecDetailRow")
	public String addSpecDetailRow(@ModelAttribute("product") Product product,
			@RequestParam("addSpecDetailRow") Integer rowIndex) {
		ProductSpec productSpec = product.getProductSpecs().get(rowIndex.intValue());
		ProductSpecDetail detail = new ProductSpecDetail();
		detail.setProductSpec(productSpec);
		productSpec.getProductSpecDetails().add(detail);
		return "admin/products/add";
	}

	@PostMapping(params = "removeSpecRow")
	public String removeSpecRow(@ModelAttribute("product") Product product,
			@RequestParam("removeSpecRow") Integer rowIndex) {
		System.err.println("Product specification size: " + product.getProductSpecs().size());
		product.getProductSpecs().remove(rowIndex.intValue());
		return "admin/products/add";
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

		return "admin/products/add";
	}

	@PostMapping(params = "create")
	public String create(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile image,
			Errors errors, SessionStatus status) {

		validator.validate(product, errors);

		if (errors.hasFieldErrors()) {
			return "admin/products/add";
		}

		if (!image.isEmpty()) {
			try (InputStream in = image.getInputStream()) {

				String brandFolder = product.getBrand().getName().toLowerCase().replaceAll("\\s+", "");
				String productName = StringUtils.formatProductName(product.getName());
				String productThumbnail = productName + ".png";

				String localPath = String.format("src/main/resources/static/images/products/%s/%s", brandFolder,
						productThumbnail);
				String databasePath = String.format("/images/products/%s/%s", brandFolder, productThumbnail);

				Path target = Paths.get(localPath);
				Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
				product.setThumbnail(databasePath);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			productService.create(product);
			status.setComplete();
			return "redirect:/admin/products";
		} catch (DuplicateFieldException e) {
			errors.rejectValue("name", "product.name", "Trùng tên sản phẩm!");
			return "admin/products/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "admin/products/add";
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
