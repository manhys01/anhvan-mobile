package poly.agile.webapp.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.exception.DuplicateFieldException;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;
import poly.agile.webapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Product create(Product p) {
		Product product = findProductByName(p.getName());
		if (product != null)
			throw new DuplicateFieldException();
		return repository.save(p);
	}

	@Override
	public Product update(Product p) {
		Product product = findProductByName(p.getName());
		if (product != null)
			if (!product.getId().equals(p.getId()))
				throw new DuplicateFieldException();
		return repository.save(p);
	}

	@Override
	public boolean remove(Product object) {
		try {
			repository.delete(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<ProductDTO> list(int page) {
		if (page <= 0)
			page = 1;
		return repository.findProductBy(PageRequest.of(page - 1, 8));
	}

	@Override
	public Product findById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public List<ProductDTO> newProducts() {
		return repository.findFiveLastestProducts();
	}

	@Override
	public Product findProductByBrand(Brand brand) {
		return repository.findByBrand(brand);
	}

	@Override
	public Product findProductByName(String name) {
		if (name == null)
			throw new NullPointerException();
		return repository.findByName(name);
	}
}
