package njit.service;

import java.sql.Date;

import njit.model.Booklimitation;

public interface BooklimitationService extends BaseService<njit.model.Booklimitation>{

	public Booklimitation selectTimeCodeBySidAndDate(Integer sid, Date date);

}
