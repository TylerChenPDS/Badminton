package njit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import njit.dao.BaseDao;
import njit.dao.NoticeDao;
import njit.model.Notice;
import njit.model.User;

@Service("noticeService")
public class NoticeServiceImp extends BaseServiceImp<Notice> implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	@Override
	public BaseDao getBaseDao() {
		return noticeDao;
	}
	@Override
	public PageInfo<Notice> selectNoticesByPage(int pageNum, int size) {
		Page<Notice> pager = PageHelper.startPage(pageNum,size);
		PageInfo<Notice> info = new PageInfo<>(noticeDao.findAll());
		return info;
	}
	@Override
	public void issueNotice(Notice notice) {
		this.addForNotMatch(new Object[] {
				"title",
				"time",
				"text"
		}, new Object[] {
				notice.getTitle(),
				notice.getTime(),
				notice.getText()
		});
	}
	@Override
	public void batchDel(Integer[] nidArr) {
		for(Integer nid : nidArr) {
			this.delete(nid);
		}
	}

}
