package njit.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import njit.model.Booking;
import njit.model.Notice;
import njit.model.Picture;
import njit.model.Stadium;
import njit.model.User;
import njit.service.BookingService;
import njit.service.NoticeService;
import njit.service.PictureService;
import njit.service.StadiumService;
import njit.web.AuthClass;
import njit.web.AuthMethod;

@AuthClass
@Controller
public class IndexController {
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private StadiumService stadiumService; 
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private NoticeService noticeService;
	
	
	@RequestMapping(value= {"/","","/index","/index.html"})
	public String index(Model model) {
		PageInfo<Notice> notices = noticeService.selectNoticesByPage(1, 10);
		model.addAttribute("notices", notices);
		return "index";
	}
	@RequestMapping(method=RequestMethod.GET,value= {"/login.html","/login"})
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/register.html")
	public String registerView() {
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/findpassword.html")
	public String findPassword() {
		return "findpassword";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/checknotice.html")
	public String checkNotice(Model model,@RequestParam("nid")Integer nid) {
		Notice notice = noticeService.select(nid);
		model.addAttribute("notice", notice);
		return "checknotice";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/checkstadium.html")
	public String checkStadium(Model model) {
		List<Stadium> stadiums = stadiumService.selectAll();
		model.addAttribute("stadiums", stadiums);
		return "checkstadium";
	}
	
	//查看预定信息
	@AuthMethod("user")
	@RequestMapping(method=RequestMethod.GET,value="/bookinformation.html")
	public String bookInformation(Model model,HttpSession session) {
		User user = (User) session.getAttribute("logineduser");
		
		Date today = new Date(System.currentTimeMillis());
		
		List<Booking> bookings = bookingService.selectTodayBookingDatas(user.getId(),today);
		
		model.addAttribute("bookings", bookings);
		
		SimpleDateFormat si = new  SimpleDateFormat("HH:m");
		String[] timecodes = si.format(today).split(":");
		int nowTimeCode = 0;
		Integer hh = Integer.parseInt(timecodes[0]);
		int mm = Integer.parseInt(timecodes[1]);
		if(mm >= 30)
			nowTimeCode = hh * 2 + 2;
		else
			nowTimeCode = hh * 2 + 1;
		
		model.addAttribute("nowTimeCode", nowTimeCode);
		return "bookinformation";
	}
	
	//出错页面
	@RequestMapping(value="/error.html",method=RequestMethod.GET)
	public String error404() {
		return "error";
	}
	
	//所有用户查看所有通知的页面
	@RequestMapping(value="/allnotice.html",method=RequestMethod.GET)
	public String allnotice(
			HttpSession session,
			Model model,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="20")int size) {
		
		PageInfo<Notice> notices = noticeService.selectNoticesByPage(pageNum, size);
		model.addAttribute("notices", notices);
		
		session.removeAttribute("searchpage");
		session.removeAttribute("starttime");
		session.removeAttribute("endtime");
		return "allnotice";
	}
	
	
	
	
	//通过 图片id获取所有图片的方法
	@ResponseBody
	@RequestMapping(value="/getpic/{id}",method=RequestMethod.GET)
	public byte[] getPicture(@PathVariable("id")Integer id) {
		Picture pic = pictureService.select(id);
		return pic != null ? pic.getPicture() : null;
	}
	
}
