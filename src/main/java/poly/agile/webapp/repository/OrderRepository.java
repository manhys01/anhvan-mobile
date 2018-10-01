package poly.agile.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.model.Order;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT new poly.agile.webapp.dto.OrderDTO(o.id, o.customerName, o.phoneNumber, o.address, o.createdTime, o.status.name) "
			+ "FROM Order o")
	Page<OrderDTO> getPages(Pageable pageable);

}
