package njit.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.Booklimitation;

public interface BooklimitationDao extends  BaseDao{

	Booklimitation selectTimeCodeBySidAndDate(@Param("sid")Integer sid, @Param("date")Date date);

	List<Booklimitation> selectAllLimitsRelStadium();

	Integer isExist(@Param("date")java.util.Date time, @Param("sid")Integer sid);

}
