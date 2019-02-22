package njit.dao;

import org.apache.ibatis.annotations.Param;

public interface ResourceDao extends BaseDao{

	public int isExitByPath(@Param("path")String path);

}
