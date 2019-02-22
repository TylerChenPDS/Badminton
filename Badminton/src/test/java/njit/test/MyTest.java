package njit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import njit.service.UserService;

public class MyTest {
	@Test
	public  void testMyBatis() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ctx.getBean("userService",UserService.class).addForNotMatch(new Object[] {"stuno","password","telephone","email"}, new Object[] {"202161011","202161011","17624573673","1969922921@qq.com"});;
		
	}
}
