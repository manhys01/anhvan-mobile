package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.model.OrderStatus;

@Transactional
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

}
