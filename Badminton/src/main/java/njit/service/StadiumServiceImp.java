package njit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.StadiumDao;
import njit.model.Stadium;

@Service("stadiumService")
public class StadiumServiceImp extends BaseServiceImp<Stadium> implements StadiumService{
	@Autowired
	private StadiumDao stadiumDao;
	
	@Override
	public BaseDao getBaseDao() {
		return stadiumDao;
	}

}
