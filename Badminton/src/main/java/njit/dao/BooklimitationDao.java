package njit.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Param;

import njit.model.Booklimitation;

public interface BooklimitationDao extends  BaseDao{

	Booklimitation selectTimeCodeBySidAndDate(@Param("sid")Integer sid, @Param("date")Date date);

}
