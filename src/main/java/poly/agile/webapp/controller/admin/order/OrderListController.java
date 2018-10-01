package poly.agile.webapp.controller.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.service.order.OrderService;
import poly.agile.webapp.util.pagination.Pagination;

@Controller
@RequestMapping("/admin/orders")
public class OrderListController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public String all(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
		Page<OrderDTO> pages = orderService.getPages(page);
		model.addAttribute("orders", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		return "admin/orders/list";
	}
	
	@ModelAttribute("adminOrderPage")
	public boolean active() {
		return true;
	}

}
