package njit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import njit.model.Role;
import njit.model.User;
import njit.service.RoleService;
import njit.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/admin.html",method=RequestMethod.GET)
	public String adminView() {
		return "admin/admin";
	}
	
	@RequestMapping(value="/admin/role_manager.html",method=RequestMethod.GET)
	public String roleManager() {
		
		return "admin/role_manager";
	}
	
	@RequestMapping(value="/admin/user_manager.html",method=RequestMethod.GET)
	public String userManager(Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum, @RequestParam(value="size",defaultValue="5")int size) {
		List<Role> roles = roleService.selectAll();
		model.addAttribute("allroles", roles);
		PageInfo<User> users = userService.selectUsersByPage(pageNum, size);
		model.addAttribute("userDatasByPager", users);
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
