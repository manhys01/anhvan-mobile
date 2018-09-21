package poly.agile.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p")
	List<ProductDTO> findProductBy(Pageable page);

}
