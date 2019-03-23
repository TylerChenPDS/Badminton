package njit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import njit.model.Role;
import njit.model.User;
import njit.service.RoleService;
import njit.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	//用户登陆
	@RequestMapping(value="login.html",method=RequestMethod.POST)
	public String login(Model model,@RequestParam("userinfo") String userinfo,
			@RequestParam("password") String password,
			HttpSession session) {
		User user = userService.login(userinfo,password);
		if(user == null) {
			model.addAttribute("err", "用户名或密码错误");
			return "/login";
		}
		session.setAttribute("logineduser", user);
		return "redirect:/index.html";
	}
	
	
	
	
	
	
	//管理员向数据库中添加一条用户的记录, roleids 代表该用户的角色。
	@RequestMapping(value="/admin/addUser.html",method=RequestMethod.POST)
	public String addUser(User user, Integer roleid) {
		userService.addUserAndRole(user,roleid);
		return "redirect:/admin/user_manager.html";
	}
	
	//删除一条用户的记录
	@RequestMapping(value="admin/deleteUser.html",method=RequestMethod.GET)
	public String deleteUser(@RequestParam("id") Integer id) {
		userService.deleteUserAndUserRolebyUid(id);
		return "redirect:/admin/user_manager.html";
	}
	
	//批量删除用户
	@ResponseBody
	@RequestMapping(value="admin/deleteUsers.html",method=RequestMethod.POST)
	public String deleteUsers(@RequestParam("uids") String uids) {
		System.out.println(uids);
		uids = uids.replaceAll("\\[|\\]|\"", "");
		String[] strs = uids.split(",");
		Integer[] uidArr = new Integer[strs.length];
		int i = 0;
		for(String id : strs) 
			uidArr[i ++] = Integer.parseInt(id);
		userService.batchDelUserRelateRoleByIds(uidArr);
		return "success";
	}
	
	//模糊查询
	@RequestMapping(value="admin/searchUsersByUserInfo.html",method=RequestMethod.POST)
	public String searchUsersByUserInfo(Model model,
			@RequestParam(value="userinfo",defaultValue="") String userinfo,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="5") int size) {
		
		List<Role> roles = roleService.selectAll();
		model.addAttribute("allroles", roles);
		PageInfo<User> users = userService.selectUsersByPageAndUserInfo(userinfo,pageNum, size);
//		System.out.println(users);
		model.addAttribute("userDatasByPager", users);
		return "/admin/user_manager";
	}
	
	
	@RequestMapping(value="admin/updateUser.html",method=RequestMethod.POST)
	public String updateUser(User user,@RequestParam("roleid") Integer roleid) {
		userService.updateUserAndRole(user,roleid);
		return "redirect:/admin/user_manager.html";
	}
	
	
	//更新用户视图窗口
	@ResponseBody
	@RequestMapping(value="admin/updateUserView.html",
		method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String updateUser(@RequestParam("id") Integer id,HttpServletRequest request) {
		User user = userService.selectUserAndRole(id);
		
		String url = request.getContextPath() + "/admin/updateUser.html";
		
		List<Role> roles = roleService.selectAll();
		
		String radiostr = "";
		
		for(int i = 0; i < roles.size(); i ++) {
			Role role = roles.get(i);
			if(user.getRoles().contains(role)) {
				radiostr += "<div class=\"radio radio-inline addradio\" style=\"margin-top: 0px;\">\r\n" + 
						"									<label> <input type=\"radio\" checked=\"checked\" name=\"roleid\" value=\""+ role.getId() +"\"\r\n" + 
						"										> "+ role.getRolename() +"\r\n" + 
						"									</label>\r\n" + 
						"								</div>";
			}else {
				radiostr += "<div class=\"radio radio-inline addradio\" style=\"margin-top: 0px;\">\r\n" + 
						"									<label> <input type=\"radio\"  name=\"roleid\" value=\""+ role.getId() +"\"\r\n" + 
						"										> "+ role.getRolename() +"\r\n" + 
						"									</label>\r\n" + 
						"								</div>";
			}
		}
		return  "<div class=\"modal-header\">\r\n" + 
				"					<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n" + 
				"						<span>&times;</span>\r\n" + 
				"					</button>\r\n" + 
				"					<h4 class=\"modal-title\" id=\"myModalLabel\">修改用户</h4>\r\n" + 
				"				</div>\r\n" + 
				"				<div class=\"modal-body\">\r\n" + 
				"					<form id='updateUserForm' method='post'  action='"+ url +"'>\r\n" + 
				"<input type='hidden' name='id' value='"+ user.getId() +"'/>" +
				"						<div class=\"form-group\">\r\n" + 
				"							<label>学号：</label> <input readonly='readonly' value=\""+ user.getStuno() +"\" type=\"text\" name=\"stuno\"\r\n" + 
				"								class=\"form-control\">\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"form-group\">\r\n" + 
				"							<label for=\"\">密码(密码留空代表不修改)：</label> <input class=\"form-control\"\r\n" + 
				"								type=\"password\" name=\"password\">\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"form-group\">\r\n" + 
				"							<label for=\"\">手机号：</label> <input value=\""+ user.getTelephone() +"\"\r\n" + 
				"								class=\"form-control\" type=\"text\" name=\"telephone\">\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"form-group\">\r\n" + 
				"							<label for=\"\">邮箱：</label> <input value=\""+ user.getEmail() +"\"\r\n" + 
				"								class=\"form-control\" type=\"text\" name=\"email\">\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"form-group \">\r\n" + 
				"							<label for=\"\">身份：</label>\r\n" + 
				"\r\n" + radiostr +
			
				"							\r\n" + 
				"						</div>\r\n" + 
				"					</form>\r\n" + 
				"				</div>\r\n" + 
				"				<div class=\"modal-footer\">\r\n" + 
				"					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n" + 
				"					<button type=\"button\" onclick='updateSubmit();' class=\"btn btn-primary\">编辑用户</button>\r\n" + 
				"				</div>";
	}
}
