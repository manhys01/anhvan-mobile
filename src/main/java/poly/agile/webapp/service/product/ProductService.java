package poly.agile.webapp.service.product;

import org.springframework.stereotype.Service;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.BaseService;
import poly.agile.webapp.service.DTOService;

@Service
public interface ProductService extends BaseService<Product, Integer>, DTOService<ProductDTO> {
	
}
