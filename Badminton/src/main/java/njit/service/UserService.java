package njit.service;


import com.github.pagehelper.PageInfo;

import njit.model.User;


public interface UserService extends BaseService<User>{
	/*----------------------TylerChen's APIS------------------------------*/
	
	public PageInfo<User> selectUsersByPage(int pageNum, int size);

	public void deleteUserAndUserRolebyUid(int id);

	public void batchDelUserRelateRoleByIds(Integer[] uidArr);

	public void addUserAndRole(User user, Integer roleid);

	public PageInfo<User> selectUsersByPageAndUserInfo(String userinfo, int pageNum, int size);

	public User selectUserAndRole(Integer id);

	public void updateUserAndRole(User user,Integer roleid);

	public User login(String userinfo, String password);

	/**
	 * 
	 * @param colum 数据库列名
	 * @param value 对应列名的值
	 * @return 返回一个boolean数据，true代表存在这个条数据，false代表不存在
	 */
	public boolean validateisExistByColum(String colum,Object value);

	public boolean updatePasswordStunoRelTel(String stuno, String telephone,String password);

	/**
	 * @detail 通过微信号查找数据库中对应的用户 
	 * @param weixinid
	 * @return User，若返回值不为空，则代表查找成功
	 */
	public User selectUserByWeixinid(String weixinid);

	/**
	 * @detail 通过微信号自动向数据库中注册一条用户信息
	 * @param weixinid
	 * @return User
	 */
	public User autoRegisterByWeixinid(String weixinid);

	/**
	 * @detail 将微信号与对应的邮箱绑定。
	 * @param weixinid 
	 * @param email
	 */
	public boolean bindWeixinidAndEmail(String weixinid, String email);
	
	/**
	 * @detail 通过邮箱号查找用户
	 * @param email
	 * @return
	 */
	public User selectUserByEmail(String email);
	
	/*----------------------------------------------------*/
}
