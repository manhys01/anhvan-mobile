package poly.agile.webapp.controller.admin.order;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.OrderStatus;
import poly.agile.webapp.service.order.OrderService;
import poly.agile.webapp.util.pagination.Pagination;

@Controller
@RequestMapping("/admin")
@SessionAttributes("order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public String all(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
		Page<OrderDTO> pages = orderService.getPages(page);
		model.addAttribute("orders", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		return "admin/orders/list";
	}

	@GetMapping("/order/{id}")
	public String getOrder(Model model, @PathVariable("id") Integer id) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "admin/orders/edit";
	}

	@PutMapping(value = "/order/{id}", params = "update")
	public String replaceOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
		if (result.hasErrors())
			return "admin/orders/edit";

		// update
		Order o = orderService.update(order);

		if (o != null)
			return "redirect:/admin/orders";

		return "admin/orders/edit";
	}

	@ModelAttribute("statuses")
	public List<OrderStatus> getOrderStatus() {
		return orderService.findAllOrderStatus();
	}

	@ModelAttribute("adminOrderPage")
	public boolean active() {
		return true;
	}

}
