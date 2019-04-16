package njit.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import njit.model.Booking;
import njit.model.Booklimitation;
import njit.model.Notice;
import njit.model.Stadium;
import njit.model.User;
import njit.model.toolbean.TimeBeginAndEnd;
import njit.service.BookingService;
import njit.service.BooklimitationService;
import njit.service.NoticeService;
import njit.service.StadiumService;
import njit.service.UserService;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	
	@Value("${mail.smtp.username}")
	private String fromMailAccount;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	StadiumService stadiumService;
	
	@Autowired
	BooklimitationService booklimitationService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	TimeBeginAndEnd timeBeginAndEnd;
	
	/**
	 * @detail 微信登陆或者注册，即用户刚进去的时候都要这样一下
	 * @return PageInfo&lt;Notice&gt; 返回通知以及当前的页数等信息
	 */
	@ResponseBody
	@RequestMapping(value="/loginorregister")
	public PageInfo<Notice> login(@RequestParam("weixinid") String weixinid,HttpSession session) {
		//1，查找数据库中是否有这个weixinid
		boolean flag = userService.validateisExistByColum("weixinid",weixinid);
		
		User weixinuser = null;
		if(flag) {//存在
			weixinuser = userService.selectUserByWeixinid(weixinid);
		}else {//对用的微信用户不存在
			//自动注册，即向数据库中添加一条记录
			weixinuser = userService.autoRegisterByWeixinid(weixinid);
		}
		
		//将对应的用户加到session域中
		session.setAttribute("weixinuser", weixinuser);
		
		//将通知返回给微信客户端
		PageInfo<Notice> notices = noticeService.selectNoticesByPage(1, 8);
		return notices;
	}
	
	
	/**
	 * @detail 查看通知，传入参数为分页信息
	 * @return PageInfo&lt;Notice&gt; 返回通知以及当前的页数等信息
	 */
	@ResponseBody
	@RequestMapping(value="/checknotice")
	public PageInfo<Notice> checkNotice(
			@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			@RequestParam(value="size",defaultValue="8")Integer size){
		PageInfo<Notice> notices = noticeService.selectNoticesByPage(pageNum, size);
		return notices;
	}
	
	//微信用户绑定邮箱
	@RequestMapping(value="/validateEmail",method=RequestMethod.GET)
	public String bindWithEmail(Model model,@RequestParam("email")String email,@RequestParam("weixinid")String weixinid) {
		boolean flag = userService.bindWeixinidAndEmail(weixinid,email);
		if(flag) {
			model.addAttribute("bingmess", "绑定成功！");
		}else {
			model.addAttribute("bingmess", "绑定失败！");
		}
		return "/weixin/success";
	}
	
	//发送验证邮箱,返回success代表发送成功
	@ResponseBody
	@RequestMapping("/sendValidateEmail")
	public String sendValidateEmail(@RequestParam("weixinid")String weixinid,@RequestParam("email")String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		// 发件人的邮箱地址
		message.setFrom(fromMailAccount);
		// 收件人的邮箱地址
		message.setTo(email);
		// 邮件主题
		message.setSubject("验证消息");
		// 邮件内容
		message.setText("你好！你正在对微信小程程序羽毛球场馆预定系统进行邮箱绑定，请点击http://localhost:8080/Badminton/weixin/validateEmail"+ "?email="+email + "&weixinid=" + weixinid + " 进行验证。如非本人操作请忽略！！");
		// 发送邮件
		javaMailSender.send(message);
		return "success";
	}

	//根据对应参数获得可用场地
	@ResponseBody
	@RequestMapping(value="/getUseableStadiums")
	public List<Stadium> getUseableStadiums(@RequestParam("timecodes")String timecodes, 
			@RequestParam("date")Date date){
		
		Date now = new Date(System.currentTimeMillis());
		
		//已经过的事件点不能选
		SimpleDateFormat si = new  SimpleDateFormat("HH:m");
		String[] timecodes1 = si.format(now).split(":");
		int nowTimeCode = 0;
		Integer hh = Integer.parseInt(timecodes1[0]);
		int mm = Integer.parseInt(timecodes1[1]);
		if(mm >= 30)
			nowTimeCode = hh * 2 + 2;
		else
			nowTimeCode = hh * 2 + 1;
		
		
		
		String []times = timecodes.split(",");
		Integer[] timesint = new Integer[times.length];
		for(int i = 0; i < times.length; i ++) {
			timesint[i] = Integer.parseInt(times[i]);
			//如果预定的时间超出限制，则没有可以预定的场地，或者是预定时间小于当前时间
			if(timesint[i] <= nowTimeCode || timesint[i] > timeBeginAndEnd.getEndtime())
				return null;
			
			
		}
		List<Stadium> allstadiums = stadiumService.selectAll();
		Map<Integer, Boolean> sid_isLimited = new HashMap<>();
		for(Stadium sta : allstadiums) {
				sid_isLimited.put(sta.getId(), true);//初始状态下设置该场馆为true,代表该场馆为true
				
				//找出对应时间段本场地是否被限制
				Booklimitation booklimitation =  booklimitationService.selectTimeCodeBySidAndDate(sta.getId(), date);
				if(booklimitation != null) {
					List<Integer> limitedcodes = booklimitation.getTimeCodes();
					for(Integer timeint : timesint) {
						//如果限制码包含其中，所选时间段的编码中的任意一个，都会导致该场地不可用
						if(limitedcodes.contains(timeint)) {
							sid_isLimited.put(sta.getId(), false);
							break;
						}
					}
				}
				//对应场地在该时间段是否已经被预定过了
				List<Booking> bookings = bookingService.selectBySidAndDate(sta.getId(), date);
				if(bookings != null && bookings.size() != 0) {
					a:for(Booking booking : bookings) {
						for(Integer timeint : timesint) {
							if(booking.getTimecode() == timeint)
								sid_isLimited.put(sta.getId(), false);
								break a;//直接不用循环了
						}
					}
				}
		}
		//删除被限制的所有场馆并返回,这个方法的测试见src/test/java/njit/test/MyTest.java中的 test1;
		System.out.println(sid_isLimited);
		allstadiums.removeIf(new Predicate<Stadium>() {
			@Override
			public boolean test(Stadium t) {
				if(sid_isLimited.get(t.getId()))
					return false;
				return true;
			}
		});
		return allstadiums;
	}
	
	/**
	 * @detail 微信实现预定的功能
	 */
	@ResponseBody
	@RequestMapping(value="/tacklebook")
	public String tacklebook(
			@RequestParam("timecodes")String timecodes,
			@RequestParam("date")Date date,
			@RequestParam("sid")Integer sid,
			@RequestParam("weixinid")String weixinid) {
		User user = userService.selectUserByWeixinid(weixinid);
		if(user != null)
			bookingService.insertWeiXinBookRecords(user.getId(), sid, date, timecodes);
		return "success";
	}
	
	/**
	 * @detail 返回用用户的所有预定信息
	 */
	@ResponseBody
	@RequestMapping(value="/checkUserBookingInformation")
	public List<Booking> checkUserBookingInformation(@RequestParam("weixinid")String weixinid){
		Date today = new Date(System.currentTimeMillis());
		return bookingService.selectByWeixinNotBeforeToday(weixinid);
	}
	
	/**
	 * @detail 处理用户退订的请求
	 * @param weixinid 微信号
	 * @param timecodes 时间编码
	 * @param date 日期编号
	 * @param sid 场馆号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/unsubscribethebook")
	public String unsubscribethebook(
			@RequestParam("weixinid")String weixinid, 
			@RequestParam("timecodes")String timecodes, 
			@RequestParam("date")Date date, 
			@RequestParam("sid")Integer sid) {
		
		
		User user = userService.selectUserByWeixinid(weixinid);
		if(user != null) {
			String timecodestrs[] = timecodes.split(",");
			for(String timecodestr : timecodestrs) {
				bookingService.unsubscribe(user.getId(), sid, date, Integer.parseInt(timecodestr));
			}
		}
		return "success";
	}
}
