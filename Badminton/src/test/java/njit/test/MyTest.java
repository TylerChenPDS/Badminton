package njit.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;

import njit.dao.BaseDao;
import njit.dao.BooklimitationDao;
import njit.model.User;
import njit.model.UserRole;
import njit.service.BooklimitationService;
import njit.service.UserRoleService;
import njit.service.UserService;

public class MyTest {
	@Test
	public  void testMyBatis() {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//			UserService userService = ctx.getBean("userService",UserService.class);
//			User user = new User();
//			user.setEmail("123456789@qq.com");
//			user.setPassword("1312313214");
//			user.setStuno("202161113");
//			user.setTelephone("123");
//			userService.addUserAndRole(user, 2);
			BooklimitationService bs = ctx.getBean(BooklimitationService.class);
			/*bs.addForNotMatch(new Object[] {
					"sid",
					"date",
					"timecode"
			}, new Object[] {
					3,
					new java.util.Date(),
					"35,36,37,38"
			});*/
			BooklimitationDao bd = ctx.getBean(BooklimitationDao.class);
			int a = bd.isExist(new java.util.Date(), 1);
			System.err.println(  "------------------------------------------------"+a);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUserRoleService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserRoleService userRoleService = ctx.getBean("userRoleService",UserRoleService.class);
		userRoleService.addForNotMatch(new Object[] {
				"uid",
				"rid"
		}, new Object[] {
				10,
				1
		});
	}
	
	@Test
	public void testStr() {
		String a = "UserRoleAloBsada";
		a.replaceAll("([A-Z])", "_" + "($0)".toLowerCase());
		System.out.println(a);
		String regex = "([A-Z])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(a);
		int i = 0;
		String re = "";
		while(m.find()) {
			int c = m.start();
			if(c != 0) {
				re += a.substring(i + 1,c) + "_" + m.group(1).toLowerCase();
				i = c;
			}else {
				re +=	a.substring(0,1).toLowerCase();
			}
			
		}
		System.out.println((re += a.substring(i + 1)));
	}
	
	@Test
	public void testArr() {
		Integer[] a = {1,2,9,7};
		Arrays.sort(a);
		Arrays.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for(int b : a) {
			System.out.print(b + " ");
		}
		System.out.println();
		Arrays.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		for(int b : a) {
			System.out.print(b + " ");
		}
		System.out.println();
	}
	
	@Test
	public void testDate() {
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
	}
	
	
	@Test
	public void test() {
		String options = "";
		for(int i = startTimeCode; i < endTimeCode; i ++) {
				options += "<option value='"+ i +"'>"+ timeCodetoTime.get(i) +"</option>\r\n";
		}
		System.out.println(options);
	}
	
	@Test
	public void testCalendar() throws ParseException {
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(si.parse("2019-4-2"));
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(new java.sql.Date(si.parse("2019-4-1").getTime()));
//		System.out.println(ca.getTime());
//		System.out.println(ca1.getTime());
		System.out.println(new java.sql.Date(si.parse("2019-4-1").getTime()).compareTo(si.parse("2019-4-2")));
		
	}
	
	@Test
	public void test1() {
		List<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < 21; i ++) {
			ints.add(i);
		}
		ints.removeIf(new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				if(t % 2 == 0)//删除所有偶数
					return true;
				return false;
			}
		});
		System.out.println(ints);
	}
	
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
	
	private static int startTimeCode = 17;//08:00
	
	private static int endTimeCode = 43;//21:00
	
	
	
}
