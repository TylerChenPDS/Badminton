package njit.dao;

import org.apache.ibatis.annotations.Param;

public interface UserRoleDao extends BaseDao{

	public void updateRoleidByUid(@Param("uid")Integer uid, @Param("roleid")Integer roleid);

}
