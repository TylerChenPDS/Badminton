package njit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import njit.model.User;

public interface UserDao extends BaseDao {

	/**
	 * 通過user的id 查詢对应的user对象，关联上role对象
	 */
	public User selectRelatedUser(@Param("id") Integer uid);
	
	public List<User> selectRelatedUsers();

	public void deleteUserRolebyUid(@Param("id")Integer id);

	//通过学号查找用户
	public User selectUserByStuno(@Param("stuno")String stuno);

	public List<User> selectRelatedUsersByUserInfo(@Param("userinfo")String userinfo);

	public User login(@Param("userinfo")String userinfo, @Param("password")String password);

	public int validateisExistByColum(@Param("column")String string, @Param("value")Object value);

	public int updatePasswordStunoRelTel(@Param("stuno")String stuno, @Param("telephone")String telephone, @Param("password")String password);
}
