package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
