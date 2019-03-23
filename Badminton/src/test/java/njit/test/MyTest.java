package njit.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;

import njit.dao.BaseDao;
import njit.model.User;
import njit.model.UserRole;
import njit.service.UserRoleService;
import njit.service.UserService;

public class MyTest {
	@Test
	public  void testMyBatis() {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
			UserService userService = ctx.getBean("userService",UserService.class);
			User user = new User();
			user.setEmail("123456789@qq.com");
			user.setPassword("1312313214");
			user.setStuno("202161113");
			user.setTelephone("123");
			userService.addUserAndRole(user, 2);
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
	
	
}
