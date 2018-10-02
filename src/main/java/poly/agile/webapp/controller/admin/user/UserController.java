package poly.agile.webapp.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.agile.webapp.service.user.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String all(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/users/list";
	}

	@ModelAttribute("adminUserPage")
	public boolean active() {
		return true;
	}
}
