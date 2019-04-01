package njit.service;

import java.sql.Date;

import com.github.pagehelper.PageInfo;

import njit.model.Booklimitation;

public interface BooklimitationService extends BaseService<njit.model.Booklimitation>{

	public Booklimitation selectTimeCodeBySidAndDate(Integer sid, Date date);

	public PageInfo<Booklimitation> selectAllLimitsRelStadium(int pageNum, int size);

}
