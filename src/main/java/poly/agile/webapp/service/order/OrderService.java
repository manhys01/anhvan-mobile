package poly.agile.webapp.service.order;

import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.OrderStatus;
import poly.agile.webapp.service.BaseService;

public interface OrderService extends BaseService<Order, Integer>{
	
	OrderStatus findOrderStatusById(Integer id);
	
}
