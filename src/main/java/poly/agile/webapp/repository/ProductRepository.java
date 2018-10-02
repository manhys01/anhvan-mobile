package poly.agile.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.ProductDTO;
import poly.agile.webapp.model.Brand;
import poly.agile.webapp.model.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	Product findByBrand(Brand brand);

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p WHERE p.brand.name like :search OR p.name like :search")
	Page<ProductDTO> findProduct(@Param("search") String keyword, Pageable pageable);

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p")
	Page<ProductDTO> findProductBy(Pageable page);

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p ORDER BY p.createdTime desc")
	List<ProductDTO> findLastestProducts(Pageable page);

	default List<ProductDTO> findFiveLastestProducts() {
		return findLastestProducts(PageRequest.of(0, 5));
	}

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p WHERE p.brand = :brand")
	Page<ProductDTO> findProductByBrand(@Param("brand") Brand brand, Pageable pageable);

	@Query("SELECT new poly.agile.webapp.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail) "
			+ "FROM Product p WHERE p.id = :id")
	ProductDTO findProductById(@Param("id") Integer id);

	@Modifying
	@Query("UPDATE Product p SET p.view = p.view + 1 WHERE p.id= :id")
	void incrementViewCount(@Param("id") Integer id);
	
}
