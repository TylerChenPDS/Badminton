package njit.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import njit.model.Booking;
import njit.model.Booklimitation;
import njit.model.User;
import njit.service.BookingService;
import njit.service.BooklimitationService;

@Controller
public class BookController {
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
	
	private int startTimeCode = 17;//08:00
	
	private int endTimeCode = 48;//21:00
	
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BooklimitationService booklimitationService;
	
	@ResponseBody
	@RequestMapping(value="/bookView.html",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String bookView(@RequestParam("sid")Integer sid,HttpServletRequest request) {
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
		
		String url = request.getContextPath() + "/booking.html";
		
		String options = "";
		for(int i = startTimeCode; i < endTimeCode; i ++) {
			if(limitTimeCodes.contains(i) || i  <= nowTimeCode)
				options += "<option disabled value='"+ i +"'>"+ timeCodetoTime.get(i) + "（不可选）" +"</option>\r\n";
			else
				options += "<option value='"+ i +"'>"+ timeCodetoTime.get(i) +"</option>\r\n";
		}
		return "<div class='modal-header'>\r\n" + 
				"                    <button type='button' class='close' data-dismiss='modal'>\r\n" + 
				"                        <span>&times;</span>\r\n" + 
				"                    </button>\r\n" + 
				"                    <h4 class='modal-title' id='myModalLabel'>预定场地</h4>\r\n" + 
				"                </div>\r\n" + 
				"                <div class='modal-body'>\r\n" + 
				"                    <!-- 这里面的内容是动态改变的 -->\r\n" + 
				"                    <form id='bookForm' method='POST' action='"+ url +"'>\r\n" + 
				"                        <input type='hidden' name='id' value=''>\r\n" + 
				"                        <div class='form-group'>\r\n" + 
				"                            <input type='text' value='"+ sid +"' readonly='readonly' name='sid' class='form-control'>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class='form-group'>\r\n" + 
				"                            <select data-live-search='true' name='timecode' multiple class='selectpicker form-control'>\r\n" + options +
				"                            </select>\r\n" + 
				"                        </div>\r\n" + 
				"                    </form>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"modal-footer\">\r\n" + 
				"                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n" + 
				"                    <button onclick='submitBook()' type=\"button\" class='btn btn-primary'>预定</button>\r\n" + 
				"                </div>";
	}
	
	
	//处理预定
	@RequestMapping(value="/booking.html",method=RequestMethod.POST)
	public String tackleBooking(Model model,@Param("sid")Integer sid,@Param("timecode")Integer []timecode,HttpSession session) {
		Date date = new Date(System.currentTimeMillis());
		User user = (User) session.getAttribute("logineduser");
		if(user != null && timecode != null) {
			bookingService.insertBookRecords(user.getId(),sid,date,timecode);
		}
		model.addAttribute("feedback", "预定成功！！");
		return "redirect:/checkstadium.html";
	}
}
