package njit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.UserDao;
import njit.model.User;
@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}

	

}
