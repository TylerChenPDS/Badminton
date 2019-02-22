package njit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.ResourceDao;
import njit.model.Resource;

@Service("resourceService")
public class ResourceServiceImp extends BaseServiceImp<Resource> implements ResourceService{
	@Autowired
	private ResourceDao resourceDao;
	@Override
	public BaseDao getBaseDao() {
		return resourceDao;
	}
	@Override
	public void initPath(List<String> paths) {
		//把数据插入到数据库
		Object[] filedNames = new Object[] {"path"};
		int isExit = 0;//0表示不存在
		for(String path : paths) {
			isExit = resourceDao.isExitByPath(path);
			System.err.println(isExit);
			if(isExit == 0)//不存在才插入
				resourceDao.addForNotMatch("t_resource", filedNames, new Object[] {path});
		}
	}

}
