package njit.service;

import java.sql.Date;
import java.util.List;

import njit.model.Booking;

public interface BookingService extends BaseService<Booking>{

	List<Booking> selectBySidAndDate(Integer sid, Date date);
	
	void insertOneBookRecords(Integer uid, Integer sid, Date date, Integer timecode);
	
	void insertBookRecords(Integer uid, Integer sid, Date date, Integer[] timecodes);

	List<Booking> selectTodayBookingDatas(Integer id, Date today);

}
