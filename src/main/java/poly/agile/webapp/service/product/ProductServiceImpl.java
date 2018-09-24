package poly.agile.webapp.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import poly.agile.webapp.dto.ProductDTO;
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
	public Product create(Product object) {
		return repository.save(object);
	}

	@Override
	public Product update(Product object) {
		return repository.save(object);
	}

	
	@Override
	public boolean delete(Product object) {
		try {
			repository.delete(object);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ProductDTO> list(int page) {
		if (page <= 0)
			page = 1;
		return repository.findProductBy(PageRequest.of(page - 1, 8));
	}

	@Override
	public Product findById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public long totalPages() {
		return (long) Math.ceil(repository.count() / 8.0);
	}

	@Override
	public List<ProductDTO> newProducts() {
		return repository.findFiveLastestProducts();
	}
}
