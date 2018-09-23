package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.ProductSpec;

@Transactional
public interface ProductSpecRepository extends JpaRepository<ProductSpec, Integer> {

}
