package njit.service;

import java.sql.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;

import njit.model.Notice;

public interface NoticeService extends BaseService<Notice>{

	PageInfo<Notice> selectNoticesByPage(int pageNum, int size);

	void issueNotice(Notice notice);

	void batchDel(Integer[] nidArr);

	PageInfo<Notice> searchByDatePages(Date starttime, Date endtime, int pageNum, int size);

}
