package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
