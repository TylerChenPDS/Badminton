package njit.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.Booking;

public interface BookingDao extends BaseDao{

	List<Booking> selectBySidAndDate(@Param("sid")Integer sid, @Param("date")Date date);

	List<Booking> selectTodayBookingDatas(@Param("uid")Integer uid, @Param("today")Date today);
}
