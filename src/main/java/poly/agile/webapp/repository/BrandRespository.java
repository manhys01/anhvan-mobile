package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.Brand;

@Transactional
public interface BrandRespository extends JpaRepository<Brand, Integer> {

}
