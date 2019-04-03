package njit.service;

import java.sql.Date;

import com.github.pagehelper.PageInfo;

import njit.model.Booklimitation;

public interface BooklimitationService extends BaseService<njit.model.Booklimitation>{

	public Booklimitation selectTimeCodeBySidAndDate(Integer sid, Date date);

	public PageInfo<Booklimitation> selectAllLimitsRelStadium(int pageNum, int size);

	public void updateorAddLimitations(java.util.Date time, Integer[] sids, String timecode);
	
	public void updateorAddLimitation(java.util.Date time, Integer sid, String timecode);

	public void delete(Integer[] idArr);

}
