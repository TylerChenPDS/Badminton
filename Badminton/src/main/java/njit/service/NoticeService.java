package njit.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import njit.model.Notice;

public interface NoticeService extends BaseService<Notice>{

	PageInfo<Notice> selectNoticesByPage(int pageNum, int size);

	void issueNotice(Notice notice);

	void batchDel(Integer[] nidArr);

}
