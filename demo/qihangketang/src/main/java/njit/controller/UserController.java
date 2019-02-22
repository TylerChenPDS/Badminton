package njit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import njit.model.Role;
import njit.model.User;
import njit.service.RoleService;
import njit.service.UserService;
import njit.web.AuthClass;
import njit.web.AuthMethod;
@AuthClass
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@AuthMethod
	@RequestMapping(value="/admin/addUser.html",method=RequestMethod.POST)
	public String addUser(User user, Integer[] roleids) {
		System.out.println(user);
		userService.addUserAndRoles(user, roleids);
		return "redirect:/admin/userManager.html";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/updateUserView.html", produces = "text/html;charset=UTF-8")
	public String updateUser(@RequestParam("id") int id,HttpServletRequest request) {
		User user = userService.selectRelatedUser(id);
		List<Role> roles = roleService.selectAll();
		
		String path = request.getContextPath();
		String str1 = "";
		for(Role role : roles) {
			if(user.getRoles().contains(role))
				str1 += "<option selected value='"+ role.getId()+"'> "+role.getName()+"</option>";
			else
				str1 += "<option value='"+ role.getId()+"'> "+role.getName()+"</option>";
		}
		
		
		return "<div class=\"modal-header\">\r\n"
				+ "					<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n"
				+ "						<span>&times;</span>\r\n" + "					</button>\r\n"
				+ "					<h4 class=\"modal-title\" id=\"myModalLabel\">添加用户</h4>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-body\">\r\n"
				+ "				<!-- 这里面的内容是动态改变的 -->\r\n" + "					<form id='updateUserForm' method='POST' action=\""+ path +"/admin/updateUser.html\">\r\n"
				+ "				<input type='hidden' name='id' value='" + user.getId() +"'>		"
				+ "<div class=\"form-group\">\r\n"
				+ "							<label>用户名：</label> <input readonly='true' value=\"" + user.getUsername()
				+ "\" type=\"text\" name=\"username\"\r\n" + "								class=\"form-control\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label for=\"\">密码(留空表示不修改密码)：</label> <input value=\"\" class=\"form-control\"\r\n"
				+ "								type=\"text\" name=\"password\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label for=\"\">手机号：</label> <input value=\"" + user.getPhone() + "\"\r\n"
				+ "								class=\"form-control\" type=\"text\" name=\"phone\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label for=\"\">邮箱：</label> <input value=\"" + user.getEmail() + "\"\r\n"
				+ "								class=\"form-control\" type=\"text\" name=\"email\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>关联的角色：</label> \r\n"
				+ "							<select name=\"roleids\" data-live-search=\"true\" multiple class=\"selectpicker   form-control\">\r\n"
				+ str1
				+ "					</select>\r\n" + "						</div>\r\n" + "					</form>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-footer\">\r\n"
				+ "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n"
				+ "					<button onclick='updateUserFormSubmit();' type=\"button\" class=\"btn btn-primary\">编辑用户</button>\r\n"
				+ "				</div>";
	}
	@AuthMethod
	@RequestMapping(value="/admin/updateUser.html",method=RequestMethod.POST)
	public String updateUser(User user, int[] roleids) {
		System.err.println(user);
		System.err.println(Arrays.toString(roleids));
		userService.updateUser(user,roleids);
		return "redirect:/admin/userManager.html";
	}
	
	@AuthMethod
	@RequestMapping(value="/admin/deleteUser.html",method=RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int id) {
		userService.deleteUserRelateRole(id);
		return "redirect:/admin/userManager.html";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/admin/batchDelUsers.html",method=RequestMethod.POST)
	public String batchDelUsers(@RequestParam(value="uids")String uids) {
		uids = uids.replaceAll("\\[|\\]|\"", "");
		String[] strs = uids.split(",");
		Integer[] uidArr = new Integer[strs.length];
		int i = 0;
		for(String id : strs) 
			uidArr[i ++] = Integer.parseInt(id);
		userService.batchDelUserRelateRoleByIds(uidArr);
		return "success";
	}
	
	@AuthMethod
	@RequestMapping(value="/admin/userSearch.html",method=RequestMethod.POST)
	public String searchUsers(@RequestParam("userInfo")String userInfo,Model model) {
		List<Role> roles = roleService.selectAll();
		model.addAttribute("allRoles", roles);
		model.addAttribute("allusers", new ArrayList<>());
		PageInfo<User> users = userService.selectUsersBySearchPage(1, 5,userInfo);
		model.addAttribute("userDatasByPager", users);
		return "admin/user_manager";
	}
}
