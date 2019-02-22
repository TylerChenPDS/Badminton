package njit.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {
	@Test
	public void test() {
		String a = "[\"5\",\"7\"]";
		System.out.println(a.replaceAll("\\[|\\]|\"", ""));
	}
}
