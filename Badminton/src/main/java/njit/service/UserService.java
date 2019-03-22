package njit.service;


import com.github.pagehelper.PageInfo;

import njit.model.User;


public interface UserService extends BaseService<User>{
	public PageInfo<User> selectUsersByPage(int pageNum, int size);
	
}
