package njit.service;


import com.github.pagehelper.PageInfo;

import njit.model.User;


public interface UserService extends BaseService<User>{
	public PageInfo<User> selectUsersByPage(int pageNum, int size);

	public void deleteUserAndUserRolebyUid(int id);

	public void batchDelUserRelateRoleByIds(Integer[] uidArr);

	public void addUserAndRole(User user, Integer roleid);

	public PageInfo<User> selectUsersByPageAndUserInfo(String userinfo, int pageNum, int size);

	public User selectUserAndRole(Integer id);

	public void updateUserAndRole(User user,Integer roleid);

	public User login(String userinfo, String password);
	
}
