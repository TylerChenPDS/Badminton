package njit.dao;

import org.apache.ibatis.annotations.Param;

public interface UserRoleDao extends BaseDao{
	
	//通过用户id 删除所有关联的id
	public void deleteByUid(@Param("uid")Integer uid);

}
