package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
