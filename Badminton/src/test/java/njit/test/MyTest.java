package njit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;

import njit.model.User;
import njit.service.UserService;

public class MyTest {
	@Test
	public  void testMyBatis() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//		.addForNotMatch(new Object[] {"stuno","password","telephone","email"}, new Object[] {"202161011","202161011","17624573673","1969922921@qq.com"});;
		UserService userService = ctx.getBean("userService",UserService.class);
		PageInfo<User> users = userService.selectUsersByPage(1, 5);
		System.err.println(users.getList());
	}
}
