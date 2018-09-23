package poly.agile.webapp.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.model.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p")
	List<ProductDTO> findProductBy(Pageable page);

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p ORDER BY p.createdTime desc")
	List<ProductDTO> findLastestProducts(Pageable page);
	
	default List<ProductDTO> findFiveLastestProducts(){
		return findLastestProducts(PageRequest.of(0, 5));
	}

}
