package njit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	@RequestMapping(value="/admin/admin.html",method=RequestMethod.GET)
	public String adminView() {
		return "admin/admin";
	}
	
	@RequestMapping(value="/admin/role_manager.html",method=RequestMethod.GET)
	public String roleManager() {
		return "admin/role_manager";
	}
	
	@RequestMapping(value="/admin/user_manager.html",method=RequestMethod.GET)
	public String userManager() {
		return "admin/user_manager";
	}
	
	@RequestMapping(value="/admin/resource_manager.html",method=RequestMethod.GET)
	public String resourceManager() {
		return "admin/resource_manager";
	}
	
	@RequestMapping(value="/admin/issue_notice.html",method=RequestMethod.GET)
	public String issueNotice() {
		return "admin/issue_notice";
	}
	
	@RequestMapping(value="/admin/edit_notice.html",method=RequestMethod.GET)
	public String editNotice() {
		return "admin/edit_notice";
	}
	
	@RequestMapping(value="/admin/welcome.html",method=RequestMethod.GET)
	public String welcome() {
		return "admin/welcome";
	}
	
	
	
	
}
