package njit.service;

import java.sql.Date;
import java.util.Calendar;

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
	@Override
	public void updateorAddLimitations(java.util.Date time, Integer[] sids, String timecode) {
		for(Integer sid : sids) {
			this.updateorAddLimitation(time, sid, timecode);
		}
	}
	@Override
	public void updateorAddLimitation(java.util.Date time, Integer sid, String timecode) {
		if(booklimitationDao.isExist(time,sid) == 0) {//记录不存在，插入
			this.addForNotMatch(new Object[] {
					"sid",
					"date",
					"timecode"
			}, new Object[] {
					sid,
					time,
					timecode
			});
		}else {//记录存在,修改
			Booklimitation bo = booklimitationDao.selectTimeCodeBySidAndDate(sid, new Date(time.getTime()));
			bo.setTimecode(timecode);
			this.update(bo);
		}
	}
	@Override
	public void delete(Integer[] idArr) {
		for(Integer id : idArr) {
			this.delete(id);
		}
	}
	
	

}
