package njit.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.Notice;

public interface NoticeDao extends BaseDao{

	List<Notice> findAll();

	List<Notice> searchByDate(@Param("starttime")Date starttime,@Param("endtime") Date endtime);

}
