package njit.service;

import java.sql.Date;
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
	public List<Booking> selectTodayBookingDatas(Integer id, Date today) {
		return bookingDao.selectTodayBookingDatas(id,today);
	}

}
