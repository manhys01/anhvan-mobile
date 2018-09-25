package poly.agile.webapp.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.BaseService;
import poly.agile.webapp.service.DTOService;

@Service
public interface ProductService extends BaseService<Product, Integer>, DTOService<ProductDTO> {

	/**
	 * San pham moi nhat
	 * 
	 * @return
	 */
	public List<ProductDTO> newProducts();

	public Product findProductByBrand(Brand brand);

	public Product findProductByName(String name);

}
