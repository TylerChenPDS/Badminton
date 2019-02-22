package njit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import njit.model.Role;
import njit.model.User;
import njit.service.RoleService;
import njit.service.UserService;
import njit.web.AuthClass;
import njit.web.AuthMethod;

@AuthClass // 自定义注解 这个注解就是标记，AdminController 这个类是需要权限控制的
@Controller
public class AdminController {
	@Autowired
	private RoleService roleservice;
	@Autowired
	private UserService userService;
	
	@AuthMethod //自定义注解表示对应方法需要进行权限控制
	@RequestMapping(value="/admin/admin.html",method=RequestMethod.GET)
	public String admin() {
		return "admin/admin";
	}
	
	@AuthMethod
	@RequestMapping(value="/admin/userManager.html",method=RequestMethod.GET)
	public String userManager(Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum, @RequestParam(value="size",defaultValue="5")int size) {
		//获取所有用户角色信息，注入到user.jsp视图页面
		List<Role> roles = roleservice.selectAll();
		model.addAttribute("allRoles", roles);
		model.addAttribute("allusers", new ArrayList<>());
		PageInfo<User> users = userService.selectUsersByPage(pageNum, size);
		model.addAttribute("userDatasByPager", users);
		return "admin/user_manager";
	}
	
	@AuthMethod
	@RequestMapping(value="/admin/roleManager.html",method=RequestMethod.GET)
	public String roleManager() {
		return "admin/role_manager";
	}
	@AuthMethod
	@RequestMapping(value="/admin/resourceManager.html",method=RequestMethod.GET)
	public String resourceManager() {
		return "admin/resource_manager";
	}
}
