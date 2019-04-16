package njit.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import njit.model.Booking;
import njit.model.Booklimitation;
import njit.model.Stadium;
import njit.model.User;
import njit.model.toolbean.TimeBeginAndEnd;
import njit.model.toolbean.TimeCodeBean;
import njit.service.BookingService;
import njit.service.BooklimitationService;
import njit.service.StadiumService;
import njit.web.AuthClass;
import njit.web.AuthMethod;

@AuthClass
@Controller
public class BookController {
	
	@Autowired
	private TimeBeginAndEnd timeBeginAndEnd;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BooklimitationService booklimitationService;
	
	@Autowired
	private StadiumService stadiumService;
	
	
	@RequestMapping(value="/bookView.html",method=RequestMethod.GET)
	public String bookView(Model model,@RequestParam("sid")Integer sid,HttpServletRequest request) {
		List<Integer> limitTimeCodes = new ArrayList<>(96);
		Date date = new Date(System.currentTimeMillis());
		
		//已经过的事件点不能选
		SimpleDateFormat si = new  SimpleDateFormat("HH:m");
		String[] timecodes = si.format(date).split(":");
		int nowTimeCode = 0;
		Integer hh = Integer.parseInt(timecodes[0]);
		int mm = Integer.parseInt(timecodes[1]);
		if(mm >= 30)
			nowTimeCode = hh * 2 + 2;
		else
			nowTimeCode = hh * 2 + 1;
		
		//被限制的事件点不能选
		Booklimitation booklimitation = booklimitationService.selectTimeCodeBySidAndDate(sid,date);
		if(booklimitation != null && booklimitation.getTimecode()!= null) {
			String timeCodes[] = booklimitation.getTimecode().split(",");
			for(int i = 0;i < timeCodes.length; i ++)
				limitTimeCodes.add(Integer.parseInt(timeCodes[i]));
		}
		
		//已经被预定过的事件点不能选
		List<Booking> bookingrecords = bookingService.selectBySidAndDate(sid,date);
		for(Booking booking: bookingrecords) {
			limitTimeCodes.add(booking.getTimecode());
		}
		
		List<TimeCodeBean> canReachTimes = new ArrayList<>(48);
		
		for(int i = timeBeginAndEnd.getBegintime(); i <= timeBeginAndEnd.getEndtime(); i ++) {
			if(!limitTimeCodes.contains(i) && i > nowTimeCode) {
				canReachTimes.add(new TimeCodeBean(i));
			}else {
				canReachTimes.add(new TimeCodeBean(i,true));
			}
		}
		
		SimpleDateFormat sii = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("today", sii.format(date));
		model.addAttribute("canReachTimes", canReachTimes);
		Stadium stadium = stadiumService.select(sid);
		model.addAttribute("stadium", stadium);
		return "bookView";
	}
	
	
	
	
	
//	@ResponseBody
//	@RequestMapping(value="/bookView.html",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
//	public String bookView(@RequestParam("sid")Integer sid,HttpServletRequest request) {
//		List<Integer> limitTimeCodes = new ArrayList<>(96);
//		Date date = new Date(System.currentTimeMillis());
//		
//		//已经过的事件点不能选
//		SimpleDateFormat si = new  SimpleDateFormat("HH:m");
//		String[] timecodes = si.format(date).split(":");
//		int nowTimeCode = 0;
//		Integer hh = Integer.parseInt(timecodes[0]);
//		int mm = Integer.parseInt(timecodes[1]);
//		if(mm >= 30)
//			nowTimeCode = hh * 2 + 2;
//		else
//			nowTimeCode = hh * 2 + 1;
//		
//		//被限制的事件点不能选
//		Booklimitation booklimitation = booklimitationService.selectTimeCodeBySidAndDate(sid,date);
//		if(booklimitation != null && booklimitation.getTimecode()!= null) {
//			String timeCodes[] = booklimitation.getTimecode().split(",");
//			for(int i = 0;i < timeCodes.length; i ++)
//				limitTimeCodes.add(Integer.parseInt(timeCodes[i]));
//		}
//		
//		//已经被预定过的事件点不能选
//		List<Booking> bookingrecords = bookingService.selectBySidAndDate(sid,date);
//		for(Booking booking: bookingrecords) {
//			limitTimeCodes.add(booking.getTimecode());
//		}
//		
//		String url = request.getContextPath() + "/booking.html";
//		
//		String options = "";
//		for(int i = timeBeginAndEnd.getBegintime(); i <= timeBeginAndEnd.getEndtime(); i ++) {
//			if(limitTimeCodes.contains(i) || i  <= nowTimeCode)
//				options += "<option disabled value='"+ i +"'>"+ new TimeCodeBean(i).getTimeStr()  + "（不可选）" +"</option>\r\n";
//			else
//				options += "<option value='"+ i +"'>"+ new TimeCodeBean(i).getTimeStr() +"</option>\r\n";
//		}
//		return "<div class='modal-header'>\r\n" + 
//				"                    <button type='button' class='close' data-dismiss='modal'>\r\n" + 
//				"                        <span>&times;</span>\r\n" + 
//				"                    </button>\r\n" + 
//				"                    <h4 class='modal-title' id='myModalLabel'>预定场地</h4>\r\n" + 
//				"                </div>\r\n" + 
//				"                <div class='modal-body'>\r\n" + 
//				"                    <!-- 这里面的内容是动态改变的 -->\r\n" + 
//				"                    <form id='bookForm' method='POST' action='"+ url +"'>\r\n" + 
//				"                        <input type='hidden' name='id' value=''>\r\n" + 
//				"                        <div class='form-group'>\r\n" + 
//				"                            <input type='text' value='"+ sid +"' readonly='readonly' name='sid' class='form-control'>\r\n" + 
//				"                        </div>\r\n" + 
//				"                        <div class='form-group'>\r\n" + 
//				"                            <select data-live-search='true' name='timecode' multiple class='selectpicker form-control'>\r\n" + options +
//				"                            </select>\r\n" + 
//				"                        </div>\r\n" + 
//				"                    </form>\r\n" + 
//				"                </div>\r\n" + 
//				"                <div class=\"modal-footer\">\r\n" + 
//				"                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n" + 
//				"                    <button onclick='submitBook()' type=\"button\" class='btn btn-primary'>预定</button>\r\n" + 
//				"                </div>";
//	}
	
	
	
	//处理预定
	@AuthMethod("user")
	@RequestMapping(value="/booking.html",method=RequestMethod.POST)
	public String tackleBooking(Model model,@Param("sid")Integer sid,@Param("timecode")Integer []timecode,HttpSession session) {
		Date date = new Date(System.currentTimeMillis());
		User user = (User) session.getAttribute("logineduser");
		if(user != null && timecode != null) {
			System.err.println("123123132123123123");
			bookingService.insertBookRecords(user.getId(),sid,date,timecode);
		}else {
			System.err.println("*****************************");
		}
		model.addAttribute("feedback", "预定成功！！");
		List<Stadium> stadiums = stadiumService.selectAll();
		model.addAttribute("stadiums", stadiums);
		return "checkstadium";
	}
	
	@RequestMapping(value="/booking.html",method=RequestMethod.GET)
	public String sendRedirectBooking() {
		return "redirect:/checkstadium.html";
	}
	
	//处理退订
	@AuthMethod("user")
	@RequestMapping(value="/unsubscribe",method=RequestMethod.GET)
	public String tackleUnsubscribe(
			@RequestParam("uid")Integer uid,
			@RequestParam("sid")Integer sid,
			@RequestParam("date")Date date,
			@RequestParam("timecode")Integer timecode) {
		
		//判断
		Date now = new Date(System.currentTimeMillis());
		
		//已经过的事件点不能选
		SimpleDateFormat si = new  SimpleDateFormat("HH:m");
		String[] timecodes = si.format(now).split(":");
		int nowTimeCode = 0;
		Integer hh = Integer.parseInt(timecodes[0]);
		int mm = Integer.parseInt(timecodes[1]);
		if(mm >= 30)
			nowTimeCode = hh * 2 + 2;
		else
			nowTimeCode = hh * 2 + 1;
		
		if(timecode > nowTimeCode) {
			bookingService.unsubscribe(uid,sid,date,timecode);
		}
		return "redirect:/bookinformation.html";
	}
	
	
}
