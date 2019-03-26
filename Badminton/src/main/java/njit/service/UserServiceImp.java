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
	private UserRoleService userRoleService;
	@Autowired
	private UserDao userDao;
	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}
	
	@Override
	public void deleteUserAndUserRolebyUid(int id) {
		//1，先删除关联表中的数据
		userDao.deleteUserRolebyUid(id);
		//2，再删除用户数据
		this.delete(id);
	}
	@Override
	public void batchDelUserRelateRoleByIds(Integer[] uidArr) {
		for(Integer uid : uidArr) {
			this.deleteUserAndUserRolebyUid(uid);
		}
	}
	@Override
	public void addUserAndRole(User user, Integer roleid) {
		//1,向数据库中添加一条用户的信息
		Object[] userFileNames = new Object[] {
				"stuno",
				"password",
				"telephone",
				"email"
		};
		Object[] userValues = new Object[] {
				user.getStuno(),
				user.getPassword(),
				user.getTelephone(),
				user.getEmail()
		};
		this.addForNotMatch(userFileNames, userValues);
		
		//2,查找新插入用户的id
		User userwithid = userDao.selectUserByStuno(user.getStuno());
//		System.out.println(userwithid);
		
		//3,向UserRole表中添加关联关系
		userRoleService.addForNotMatch(new Object[] {
				"uid",
				"rid"
		}, new Object[] {
				userwithid.getId(),
				roleid
		});
	}
	
	@Override
	public PageInfo<User> selectUsersByPage(int pageNum, int size) {
		Page<User> pager = PageHelper.startPage(pageNum,size);
		PageInfo<User> info = new PageInfo<>(userDao.selectRelatedUsers());
		return info;
	}
	
	@Override
	public PageInfo<User> selectUsersByPageAndUserInfo(String userinfo, int pageNum, int size) {
		Page<User> pager = PageHelper.startPage(pageNum,size);
		PageInfo<User> info = new PageInfo<>(userDao.selectRelatedUsersByUserInfo("%"+userinfo+"%"));
		return info;
	}

	@Override
	public User selectUserAndRole(Integer id) {
		return userDao.selectRelatedUser(id);
	}

	//更新用户和角色
	@Override
	public void updateUserAndRole(User user, Integer roleid) {
		//用户没有修改的密码，代表不修改密码
		if(user.getPassword() == null || user.getPassword().trim().equals("")) {
			System.out.println(user);
			User pesistentUser = this.select(user.getId());
			user.setPassword(pesistentUser.getPassword());
		}
		
		//更新用户表中的信息
		this.update(user);
		//更新关联的角色
		userRoleService.updateRoleidByUid(user.getId(),roleid);
	}
	
	//用户登陆
	@Override
	public User login(String userinfo, String password) {
		return userDao.login(userinfo,password);
	}

	@Override
	public boolean validateisExistByColum(String colum,Object value) {
		int flag = userDao.validateisExistByColum(colum,value);
		System.out.println(flag);
		return flag != 0;
	}

	@Override
	public boolean updatePasswordStunoRelTel(String stuno, String telephone,String password) {
		int flag = userDao.updatePasswordStunoRelTel(stuno,telephone,password);
		return flag != 0;
	}

}
