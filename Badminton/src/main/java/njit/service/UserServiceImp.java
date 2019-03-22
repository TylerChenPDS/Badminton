package njit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	@Override
	public PageInfo<User> selectUsersByPage(int pageNum, int size) {
		Page<User> pager = PageHelper.startPage(pageNum,size);
		PageInfo<User> info = new PageInfo<>(userDao.selectRelatedUsers());
		return info;
	}

	

}
