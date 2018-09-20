package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.ProductSpec;

public interface ProductSpecRepository extends JpaRepository<ProductSpec, Integer> {

}
