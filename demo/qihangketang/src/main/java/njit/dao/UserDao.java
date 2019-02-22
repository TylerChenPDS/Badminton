package njit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.User;

public interface UserDao extends BaseDao{
	//根据用户名获取用户用户对象
	public User getUserByName(@Param("username")String username);

	public List<User> selectRelatedUsers();
	
	/**
	 * 通過user的id 查詢对应的user对象，关联上role对象
	 */
	public User selectRelatedUser(@Param("id")Integer uid);

	public List<User> selectUsersBySearchPage(@Param("userInfo")String userInfo);

	public User selectUserByUserInfo(@Param("userInfo")String userInfo);
	
	
	
}
