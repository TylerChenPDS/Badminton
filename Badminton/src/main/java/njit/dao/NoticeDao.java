package njit.dao;

import java.util.List;

import njit.model.Notice;

public interface NoticeDao extends BaseDao{

	List<Notice> findAll();

}
