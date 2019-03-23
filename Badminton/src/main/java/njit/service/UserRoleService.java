package njit.service;

import njit.model.UserRole;

public interface UserRoleService extends BaseService<UserRole>{

	public void updateRoleidByUid(Integer uid, Integer roleid);

}
