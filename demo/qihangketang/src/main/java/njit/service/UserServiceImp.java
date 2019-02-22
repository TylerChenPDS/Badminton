package njit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import njit.dao.BaseDao;
import njit.dao.UserDao;
import njit.dao.UserRoleDao;
import njit.model.User;

@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}
	@Override
	public void addUserAndRoles(User user, Integer[] roleids) {
		//添加用户
		//this.add(user);
		this.addForNotMatch(new Object[] {
				"username","password","email","phone","enable","add_date"
		}, new Object[] {
				user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),1,new Date()
		});
		
		//
		User u = userDao.getUserByName(user.getUsername());
		for(Integer rid : roleids) {
			userRoleDao.add("t_user_role", new Object[] {null,u.getId(),rid});
		}
		
		
	}
	@Override
	public List<User> selectRelatedUsers() {
		return userDao.selectRelatedUsers();
	}
	@Override
	public PageInfo<User> selectUsersByPage(int pageNum, int size) {
		Page<User> pager = PageHelper.startPage(pageNum,size);
		PageInfo<User> info = new PageInfo<>(userDao.selectRelatedUsers());
		return info;
	}
	@Override
	public User selectRelatedUser(Integer uid) {
		return userDao.selectRelatedUser(uid);
	}
	@Override
	public void updateUser(User user, int[] roleids) {
		if(user.getPassword().trim().equals(""))
			user.setPassword(null);
		this.update(user);
		//删除用户是id是user关联的role
		userRoleDao.deleteByUid(user.getId());
		
		Object[] fields = new Object[] {"uid","rid"};
		for(Integer rid : roleids)
			userRoleDao.addForNotMatch("t_user_role",fields , new Object[] {user.getId(),rid});
	
	}
	@Override
	public void deleteUserRelateRole(int id) {
		userRoleDao.deleteByUid(id);
		this.delete(id);
	}
	
	@Override
	public void batchDelUserRelateRoleByIds(Integer[] uidArr) {
		for(int id : uidArr)
			this.deleteUserRelateRole(id);
	}
	@Override
	public PageInfo<User> selectUsersBySearchPage(int i, int j,String userInfo) {
		Page<User> pager = PageHelper.startPage(i,j);
		PageInfo<User> info = new PageInfo<>(userDao.selectUsersBySearchPage("%"+  userInfo + "%"));
		return info;
	}
	@Override
	public User login(String userInfo, String password) {
		User user =  userDao.selectUserByUserInfo(userInfo);
		if(user == null) throw new RuntimeException("用户名或密码错误");
		if(!password.equals(user.getPassword())) {
			throw new RuntimeException("用户名或密码错误");
		}
		return user;
	}

}
