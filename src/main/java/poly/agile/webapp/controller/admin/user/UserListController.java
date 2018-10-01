package poly.agile.webapp.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.agile.webapp.service.user.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserListController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String all() {
		userService.findAll();
		return "admin/users/list";
	}

	@ModelAttribute("adminUserPage")
	public boolean active() {
		return true;
	}
}
