package njit.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.BookingDao;
import njit.model.Booking;

@Service("bookingService")
public class BookingServiceImp extends BaseServiceImp<Booking> implements BookingService{

	@Autowired
	private BookingDao bookingDao;
	
	@Override
	public BaseDao getBaseDao() {
		return bookingDao;
	}
	@Override
	public List<Booking> selectBySidAndDate(Integer sid, Date date) {
		return bookingDao.selectBySidAndDate(sid,date);
	}
	@Override
	public void insertBookRecords(Integer uid, Integer sid, Date date, Integer[] timecodes) {
		for(Integer timecode : timecodes) {
			this.insertOneBookRecords(uid, sid, date, timecode);
		}
	}
	
	
	@Override
	public void insertOneBookRecords(Integer uid, Integer sid, Date date, Integer timecode) {
		this.addForNotMatch(new Object[] {
				"uid",
				"sid",
				"date",
				"timecode",
				"signed"
		}, new Object[] {
				uid,
				sid,
				date,
				timecode,
				0
		});
		
	}
	
	@Override
	public void insertWeiXinBookRecords(Integer uid, Integer sid, Date date, String timecodes) {
		String timecodeStrs[] = timecodes.split(",");
		//通过weixinid获取用户的信息，主要是id
		
		for(String timecodesStr : timecodeStrs) {
			this.addForNotMatch(new Object[] {
					"uid",
					"sid",
					"date",
					"timecode",
					"signed"
			}, new Object[] {
					uid,
					sid,
					date,
					timecodesStr,
					0
			});
		}
	}
	@Override
	public List<Booking> selectTodayBookingDatas(Integer id, Date today) {
		return bookingDao.selectTodayBookingDatas(id,today);
	}
	@Override
	public void unsubscribe(Integer uid, Integer sid, Date date, Integer timecode) {
		bookingDao.unsubscribe(uid,sid,date,timecode);
	}
	@Override
	public List<Booking> selectByWeixinNotBeforeToday(String weixinid) {
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		Date now;
		List<Booking> bookings = null;
		try {
			now = new Date(si.parse(si.format(today)).getTime());
			bookings = bookingDao.selectByWeixinNotBeforeTheDate(weixinid,now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	

}
