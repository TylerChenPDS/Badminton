package njit.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import njit.model.Booking;

public interface BookingService extends BaseService<Booking>{

	List<Booking> selectBySidAndDate(Integer sid, Date date);
	
	void insertOneBookRecords(Integer uid, Integer sid, Date date, Integer timecode);
	
	void insertBookRecords(Integer uid, Integer sid, Date date, Integer[] timecodes);

	List<Booking> selectTodayBookingDatas(Integer id, Date today);

	void unsubscribe(Integer uid, Integer sid, Date date, Integer timecode);

	/**
	 * @detail 向预定表中插入数据
	 * @param uid 
	 * @param sid 预定信息
	 * @param date 预定日期精确到日
	 * @param timecodes 预定当日对应是时间 代码， 如 1,2代表00：00-01：00
	 */
	void insertWeiXinBookRecords(Integer uid, Integer sid, Date date, String timecodes);
	
	/**
	 * @detail 查找大于等于今天日期的预定信息
	 * @param weixinid
	 * @return
	 */
	List<Booking> selectByWeixinNotBeforeToday(String weixinid);

}
