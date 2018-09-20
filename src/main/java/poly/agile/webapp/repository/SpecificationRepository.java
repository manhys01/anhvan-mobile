package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

}
