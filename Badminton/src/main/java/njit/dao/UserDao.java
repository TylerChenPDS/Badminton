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

	/**
	 * @see 通过用户的微信号返回用户数据,关联上角色
	 * @param weixinid 用户的微信号
	 * @return User 返回一条用户数据
	 */
	public User selectUserByWeixinid(@Param("weixinid")String weixinid);

	/**
	 * @detail 通过邮箱号查找用户
	 * @param email
	 * @return
	 */
	public User selectUserByEmail(@Param("email")String email);

	/**
	 * @detail 删除对应微信的用户
	 * @param weixinid
	 */
	public void deleteUserByWeixinid(@Param("weixinid")String weixinid);
}
