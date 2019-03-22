package njit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.UserRoleDao;
import njit.model.UserRole;

@Service("userRoleService")
public class UserRoleServiceImp extends BaseServiceImp<UserRole>  implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoledao;
	
	@Override
	public BaseDao getBaseDao() {
		return userRoledao;
	}

}
