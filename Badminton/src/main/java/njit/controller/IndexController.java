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
import org.springframework.web.bind.annotation.ResponseBody;

import njit.model.Booking;
import njit.model.Picture;
import njit.model.Stadium;
import njit.model.User;
import njit.service.BookingService;
import njit.service.PictureService;
import njit.service.StadiumService;

@Controller
public class IndexController {
	static Map<Integer,String> timeCodetoTime = new HashMap<>();
	static {
		String times[] = {
				"00:00 -- 00:30","00:30 -- 01:00",
				"01:00 -- 01:30","01:30 -- 02:00",
				"02:00 -- 02:30","02:30 -- 03:00",
				"03:00 -- 03:30","03:30 -- 04:00",
				"04:00 -- 04:30","04:30 -- 05:00",
				"05:00 -- 05:30","05:30 -- 06:00",
				"06:00 -- 06:30","06:30 -- 07:00",
				"07:00 -- 07:30","07:30 -- 08:00",
				"08:00 -- 08:30","08:30 -- 09:00",
				"09:00 -- 09:30","09:30 -- 10:00",
				"10:00 -- 10:30","10:30 -- 11:00",
				"11:00 -- 11:30","11:30 -- 12:00",
				"12:00 -- 12:30","12:30 -- 13:00",
				"13:00 -- 13:30","13:30 -- 14:00",
				"14:00 -- 14:30","14:30 -- 15:00",
				"15:00 -- 15:30","15:30 -- 16:00",
				"16:00 -- 16:30","16:30 -- 17:00",
				"17:00 -- 17:30","17:30 -- 18:00",
				"18:00 -- 18:30","18:30 -- 19:00",
				"19:00 -- 19:30","19:30 -- 20:00",
				"20:00 -- 20:30","20:30 -- 21:00",
				"21:00 -- 21:30","21:30 -- 22:00",
				"22:00 -- 22:30","22:30 -- 23:00",
				"23:00 -- 23:30","23:30 -- 24:00",
				};
		
		for(int i = 1; i <= 48; i ++) {
			timeCodetoTime.put(i, times[i-1]);
		}
	}
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private StadiumService stadiumService; 
	
	@Autowired
	private PictureService pictureService;
	

	
	
	@RequestMapping(value= {"/","","/index","/index.html"})
	public String index() {
		return "index";
	}
	@RequestMapping(method=RequestMethod.GET,path= {"/login.html","/login"})
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/register.html")
	public String registerView() {
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/findpassword.html")
	public String findPassword() {
		return "findpassword";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/checknotice.html")
	public String checkNotice() {
		return "checknotice";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/checkstadium.html")
	public String checkStadium(Model model) {
		List<Stadium> stadiums = stadiumService.selectAll();
		model.addAttribute("stadiums", stadiums);
		return "checkstadium";
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.GET,path="/bookinformation.html")
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
	
	
	
	
	//通过 图片id获取所有图片的方法
	@ResponseBody
	@RequestMapping(value="/getpic/{id}",method=RequestMethod.GET)
	public byte[] getPicture(@PathVariable("id")Integer id) {
		Picture pic = pictureService.select(id);
		return pic != null ? pic.getPicture() : null;
	}
	
}
