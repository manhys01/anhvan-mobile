package poly.agile.webapp.controller.admin.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

	@GetMapping("/dashboard")
	public String goDashboard(Model model) {
		model.addAttribute("dashboardPage", true);
		return "admin/dashboard";
	}
}
