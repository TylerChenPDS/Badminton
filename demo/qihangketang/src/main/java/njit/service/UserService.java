package njit.service;

import java.util.List;


import com.github.pagehelper.PageInfo;

import njit.model.User;

public interface UserService extends BaseService<User>{

	void addUserAndRoles(User user, Integer[] roleids);
	
	/**
	 * 获取所有用户的信息
	 * @return
	 */
	public List<User> selectRelatedUsers();
	
	public PageInfo<User> selectUsersByPage(int pageNum, int size);
	
	public User selectRelatedUser(Integer uid);

	public void updateUser(User user, int[] roleids);

	public void deleteUserRelateRole(int id);

	public void batchDelUserRelateRoleByIds(Integer[] uidArr);

	public PageInfo<User> selectUsersBySearchPage(int i, int j,String userInfo);

	public User login(String userInfo, String password);
}
