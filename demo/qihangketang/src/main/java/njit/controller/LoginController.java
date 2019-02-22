package njit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import njit.model.Resource;
import njit.model.Role;
import njit.model.User;
import njit.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/login.html"},method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value= {"/login.html"},method=RequestMethod.POST)
	public String loginPost(@RequestParam("userInfo")String userInfo, @RequestParam("password")String password, HttpSession session) {
		User user = userService.login(userInfo,password);
		session.setAttribute("loginUser", user);
		List<Role> roles = user.getRoles();
		boolean isAdmin = false;
		List<Resource> loginUserRes;
		//存放当前用户所能够访问的path路径
		Set<String> canReachPaths = new HashSet<>();
		for(Role role : roles) {
			if("admin".equals(role.getCode())){
				isAdmin = true;
				break;
			}
			loginUserRes = role.getResources();
			for(Resource res : loginUserRes) {
				canReachPaths.add(res.getPath());
			}
		}
		session.setAttribute("isAdmin", isAdmin);
		session.setAttribute("canReachPaths", canReachPaths);
		return "redirect:/admin/admin.html";
	}
}
