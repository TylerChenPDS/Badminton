package njit.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import njit.model.Booking;
import njit.model.Booklimitation;
import njit.service.BookingService;
import njit.service.BooklimitationService;


public class BookTest {
	@Test
	public void testBook() {
		try {
//			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
			
//			BooklimitationService bs = ctx.getBean(BooklimitationService.class);
//			
			Date date = new Date(System.currentTimeMillis());
//			
//			Booklimitation booklimitation = bs.selectTimeCodeBySidAndDate(1	, date);
//			
//			System.out.println(booklimitation);
			
//			BookingService bis = ctx.getBean(BookingService.class);
//			
//			
//			List<Booking> bookingrecords = bis.selectBySidAndDate(1,date);
//			
//			System.out.println(bookingrecords);
			
			
//			Date date = new Date(System.currentTimeMillis());
			
			//已经过的事件点不能选
			
//			SimpleDateFormat si = new  SimpleDateFormat("HH:m");
//			System.out.println(si.format(date));
			
			
			SimpleDateFormat si = new  SimpleDateFormat("yyyy-MM-dd");
			Date date1 = new Date(si.parse("2019-3-27").getTime()) ;
			
			Date date2 = new Date(si.parse("2019-3-28").getTime()) ;
			
			System.out.println(date1.compareTo(date2));
			
			System.out.println(date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
