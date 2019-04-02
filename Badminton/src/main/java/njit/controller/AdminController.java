package njit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import njit.model.Booklimitation;
import njit.model.Notice;
import njit.model.Role;
import njit.model.Stadium;
import njit.model.User;
import njit.model.toolbean.TimeBeginAndEnd;
import njit.model.toolbean.TimeCodeBean;
import njit.service.BookingService;
import njit.service.BooklimitationService;
import njit.service.NoticeService;
import njit.service.RoleService;
import njit.service.StadiumService;
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
	
	@Autowired
	private BooklimitationService booklimitationService;  
	
	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private TimeBeginAndEnd timeBeginAndEnd;
	
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
	
	
	
	//进入修改场馆状态的页面
	@AuthMethod("admin")
	@RequestMapping(value="/admin/updateStadiumStateView.html",method=RequestMethod.GET)
	public String updateStadiumStateView(Model model) {
		List<Stadium> stadiums = stadiumService.selectAll();
		model.addAttribute("stadiums", stadiums);
		List<TimeCodeBean> times = new ArrayList<>(timeBeginAndEnd.getEndtime() - timeBeginAndEnd.getBegintime() + 1);
		for(int i = timeBeginAndEnd.getBegintime(); i <= timeBeginAndEnd.getEndtime(); i ++) {
			times.add(new TimeCodeBean(i));
		}
		model.addAttribute("times", times);
		return "admin/update_statdium_state";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/checkStadiumStateView.html",method=RequestMethod.GET)
	public String checkStadiumStateView(
			Model model,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="5")int size) {
		PageInfo<Booklimitation> booklimitations = booklimitationService.selectAllLimitsRelStadium(pageNum,size);
		model.addAttribute("booklimitations", booklimitations);
		return "admin/check_stadium_state";
	}
	
	@AuthMethod("admin")
	@RequestMapping(value="/admin/addStadiumState",method=RequestMethod.POST)
	public String addStadiumState(
			HttpSession session,
			Model model,
			@RequestParam("starttime")java.sql.Date starttime, 
			@RequestParam("endtime")java.sql.Date endtime, 
			@RequestParam("sids")Integer[] sids, 
			@RequestParam("timecodes")Integer[] timecodes) throws ParseException {
		
		List<Stadium> stadiums = stadiumService.selectAll();
		model.addAttribute("stadiums", stadiums);
		List<TimeCodeBean> times = new ArrayList<>(timeBeginAndEnd.getEndtime() - timeBeginAndEnd.getBegintime() + 1);
		for(int i = timeBeginAndEnd.getBegintime(); i <= timeBeginAndEnd.getEndtime(); i ++) {
			times.add(new TimeCodeBean(i));
		}
		model.addAttribute("times", times);
		
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		Date today = si.parse(si.format(new Date()));
		if(starttime.compareTo(today) < 0) {
			model.addAttribute("dateerr", "开始日期应不小于当前日期");
			return "admin/update_statdium_state";
		}
		
		if(starttime.compareTo(endtime) > 0) {
			model.addAttribute("dateerr", "开始日期应大于等于结束日期");
			return "admin/update_statdium_state";
		}
		
		//判断是否选了全部场馆
		for(int i = 0; i < sids.length; i ++) {
			if(sids[i] == -1) {
				List<Stadium> stadiums1 = stadiumService.selectAll();
				sids = new Integer[stadiums.size()];
				for(int j = 0; j < stadiums.size(); j ++) {
					sids[j] = stadiums1.get(j).getId();
				}
				break;
			}
		}
		
		//判断是否选择了全部时间
		for(int i = 0; i < timecodes.length; i ++) {
			if(timecodes[i] == -1) {
				timecodes = new Integer[timeBeginAndEnd.getEndtime() - timeBeginAndEnd.getBegintime() + 1];
				int k = 0;
				for(int j = timeBeginAndEnd.getBegintime(); j <= timeBeginAndEnd.getEndtime(); j ++)
					timecodes[k ++] = j;
				break;
			}
		}
		
		//添加新的约束将覆盖原有的约束
		String timecode = "";
		for(int i = 0 ; i < timecodes.length; i ++) {
			if(i != timecodes.length - 1)
				timecode += timecodes[i] + ",";
			else
				timecode += timecodes[i];
		}
		
		
		Calendar start = Calendar.getInstance();
		start.setTime(starttime);
		Calendar end = Calendar.getInstance();
		end.setTime(endtime);
		
		while(start.compareTo(end) < 0) {
			booklimitationService.updateorAddLimitations(start.getTime(),sids,timecode);
			start.add(Calendar.DATE, 1);
		}
		booklimitationService.updateorAddLimitations(end.getTime(),sids,timecode);
		
		return "redirect:/admin/checkStadiumStateView.html";
	}
	
	
	
}
