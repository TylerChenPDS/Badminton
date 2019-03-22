package njit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import njit.model.User;
import njit.service.UserService;

@Controller
public class UserController {
	
	
	
	@Autowired
	private UserService userService;
	//管理员向数据库中添加一条用户的记录, roleids 代表该用户的角色。
	@RequestMapping(value="/admin/addUser.html",method=RequestMethod.POST)
	public String addUser(User user, Integer roleids) {
		return "redirect:/admin/user_manager.html";
	}
	
	//删除一条用户的记录
	@RequestMapping(value="admin/deleteUser.html",method=RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int id) {
		//1，删除数据库中对应id的user_role中的数据
		
		
		
		//2，删除数据库中对应id的user中的数据
		
		return "redirect:/admin/user_manager.html";
	}
}
