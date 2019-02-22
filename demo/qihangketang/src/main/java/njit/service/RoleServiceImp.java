package njit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.RoleDao;
import njit.model.Role;

@Service("roleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService{
	@Autowired
	private RoleDao roledao;
	@Override
	public BaseDao getBaseDao() {
		return roledao;
	}
	

}
