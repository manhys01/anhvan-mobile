package poly.agile.webapp.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.OrderStatus;
import poly.agile.webapp.repository.OrderRepository;
import poly.agile.webapp.repository.OrderStatusRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;

	@Override
	public Order create(Order o) {
		return orderRepository.save(o);
	}

	@Override
	public Order update(Order o) {
		return orderRepository.save(o);
	}

	@Override
	public boolean remove(Order o) {
		return false;
	}

	@Override
	public Order findById(Integer id) {
		return null;
	}

	@Override
	public List<Order> findAll() {
		return null;
	}

	@Override
	public OrderStatus findOrderStatusById(Integer id) {
		return orderStatusRepository.getOne(id);
	}

}
