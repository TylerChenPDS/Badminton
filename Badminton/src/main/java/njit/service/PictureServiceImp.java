package njit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.PictureDao;
import njit.model.Picture;

@Service("pictureService")
public class PictureServiceImp extends BaseServiceImp<Picture> implements PictureService{

	@Autowired
	private PictureDao pictureDao;
	@Override
	public BaseDao getBaseDao() {
		return pictureDao;
	}

}
