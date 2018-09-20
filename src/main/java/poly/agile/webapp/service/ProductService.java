package poly.agile.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository prodRepo;
	
	public List<Product> findAll(){
		return prodRepo.findAll();
	}
	
}
