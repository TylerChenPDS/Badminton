package njit.service;

import java.sql.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;

import njit.model.Notice;

public interface NoticeService extends BaseService<Notice>{
	/*-------------------Tyler------------------------*/
	/**
	 * @param pageNum 通知信息的页数
	 * @param size 得到通知每页的大小
	 * @return PageInfo<Notice> 里面包括了分页的详细信息以及得到的所有数据
	 */
	PageInfo<Notice> selectNoticesByPage(int pageNum, int size);

	void issueNotice(Notice notice);

	void batchDel(Integer[] nidArr);

	PageInfo<Notice> searchByDatePages(Date starttime, Date endtime, int pageNum, int size);
	
	/*----------------------------------------*/

}
