package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.OrderLine;

@Transactional
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
