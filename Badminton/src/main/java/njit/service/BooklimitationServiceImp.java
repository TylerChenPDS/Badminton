package njit.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import njit.dao.BaseDao;
import njit.dao.BooklimitationDao;
import njit.model.Booklimitation;
@Service("booklimitationService")
public class BooklimitationServiceImp extends BaseServiceImp<Booklimitation> implements BooklimitationService{

	@Autowired
	private BooklimitationDao booklimitationDao;
	@Override
	public BaseDao getBaseDao() {
		return booklimitationDao;
	}
	@Override
	public Booklimitation selectTimeCodeBySidAndDate(Integer sid, Date date) {
		return booklimitationDao.selectTimeCodeBySidAndDate(sid,date);
	}
	@Override
	public PageInfo<Booklimitation> selectAllLimitsRelStadium(int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		PageInfo<Booklimitation> info = new PageInfo<>(booklimitationDao.selectAllLimitsRelStadium());
		return info;
	}

}
