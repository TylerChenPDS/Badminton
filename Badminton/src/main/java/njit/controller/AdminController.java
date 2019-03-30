package njit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import njit.model.Notice;
import njit.model.Role;
import njit.model.User;
import njit.service.NoticeService;
import njit.service.RoleService;
import njit.service.UserService;
import njit.web.AuthClass;
import njit.web.AuthMethod;

@AuthClass
@Controller
public class AdminController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NoticeService noticeService;
	
	//管理员登陆
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	public String adminlogin(Model model,@RequestParam("userinfo") String userinfo,
			@RequestParam("password") String password,
			HttpSession session) {
		User user = userService.login(userinfo,password);
		if(user == null) {
			model.addAttribute("adminerr", "用户名或密码错误！");
			return "/login";
		}
		if(!user.getRoles().get(0).getRolecode().equals("manager")) {
			model.addAttribute("adminerr", "请用管理员账号登陆！");
			return "/login";
		}
		session.setAttribute("administrator", user);
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		session.setAttribute("today", si.format(new Date()));
		return "redirect:/admin/admin.html";
	}
	
	//管理员退出登陆
	@RequestMapping(value="/adminlogout",method=RequestMethod.GET)
	public String adminlogout(HttpSession session) {
		session.removeAttribute("administrator");
		return "redirect:/login.html";
	}
	
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/admin.html",method=RequestMethod.GET)
	public String adminView() {
		return "admin/admin";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/role_manager.html",method=RequestMethod.GET)
	public String roleManager() {
		
		return "admin/role_manager";
	}
	
	
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/user_manager.html",method=RequestMethod.GET)
	public String userManager(
			HttpSession session,
			Model model,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="5")int size) {
		
		List<Role> roles = roleService.selectAll();
		model.addAttribute("allroles", roles);
		PageInfo<User> users = userService.selectUsersByPage(pageNum, size);
		model.addAttribute("userDatasByPager", users);
		session.removeAttribute("userpage");
		session.removeAttribute("userinfo");
		return "admin/user_manager";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/resource_manager.html",method=RequestMethod.GET)
	public String resourceManager() {
		return "admin/resource_manager";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/issue_notice.html",method=RequestMethod.GET)
	public String issueNotice(Model model) {
		
		Date date = new Date();
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("today", si.format(date));
		return "admin/issue_notice";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/edit_notice.html",method=RequestMethod.GET)
	public String editNotice() {
		return "admin/edit_notice";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/welcome.html",method=RequestMethod.GET)
	public String welcome() {
		return "admin/welcome";
	}
	
	//进入管理通知的页面
	@AuthMethod("admin")
	@RequestMapping(value="/admin/governnotice.html",method=RequestMethod.GET)
	public String governnotice(HttpSession session,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="5")int size) {
		PageInfo<Notice> notices = noticeService.selectNoticesByPage(pageNum,size);
		model.addAttribute("notices", notices);
		session.removeAttribute("searchpage");
		session.removeAttribute("starttime");
		session.removeAttribute("endtime");
		return "admin/governnotice";
	}
	
	
	
	
}
