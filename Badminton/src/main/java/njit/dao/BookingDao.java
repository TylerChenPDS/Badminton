package njit.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.Booking;

public interface BookingDao extends BaseDao{

	List<Booking> selectBySidAndDate(@Param("sid")Integer sid, @Param("date")Date date);

	List<Booking> selectTodayBookingDatas(@Param("uid")Integer uid, @Param("today")Date today);

	void unsubscribe(@Param("uid")Integer uid, @Param("sid")Integer sid, @Param("date")Date date, @Param("timecode")Integer timecode);

	/**
	 * @detail 传入一个微信号查找不小于date日期所有预定信息
	 * @param weixinid 微信号
	 * @param date 日期
	 * @return
	 */
	List<Booking> selectByWeixinNotBeforeTheDate(@Param("weixinid")String weixinid, @Param("date")Date date);
}
