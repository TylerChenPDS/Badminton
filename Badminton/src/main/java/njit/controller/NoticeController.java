package njit.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import njit.model.Notice;
import njit.service.NoticeService;
import njit.web.AuthClass;
import njit.web.AuthMethod;

@AuthClass
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//更新通知界面
	@AuthMethod("admin")
	@RequestMapping(value="/updateNoticeView",method=RequestMethod.GET)
	public String updateNoticeView(Model model,@RequestParam("nid")Integer nid) {
		Notice notice = noticeService.select(nid);
		model.addAttribute("notice", notice);
		return "admin/edit_notice";
	}
	
	//更新通知
	@AuthMethod("admin")
	@RequestMapping(value="updateNotice",method=RequestMethod.POST)
	public String update(Notice notice) {
		noticeService.update(notice);
		return "redirect:/admin/governnotice.html";
	}
	
	//发布通知
	@AuthMethod("admin")
	@RequestMapping(value="/issueNotice",method=RequestMethod.POST)
	public String issueNotice(Notice notice) {
		noticeService.issueNotice(notice);
		return "redirect:/admin/governnotice.html";
	}
	
	//删除对应的通知
	@AuthMethod("admin")
	@RequestMapping(value="/deleteNotice",method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("nid")Integer nid) {
		noticeService.delete(nid);
		return "redirect:/admin/governnotice.html";
	}
	
	//批量删除通知
	@AuthMethod("admin")
	@ResponseBody
	@RequestMapping(value="/deleteNotices",method=RequestMethod.POST)
	public String deleteNotices(@RequestParam("nids")String nids) {
		nids = nids.replaceAll("\\[|\\]|\"", "");
		String[] strs = nids.split(",");
		Integer nidArr[] = new Integer[strs.length];
		
		for(int i = 0; i < strs.length; i ++) {
			nidArr[i] = Integer.parseInt(strs[i]);
		}
		noticeService.batchDel(nidArr);
 		return "success";
	}
	
	
	//根据日期搜索通知
	@AuthMethod("admin")
	@RequestMapping(value="/searchNotice.html")
	public String searchNotice(
			HttpSession session,
			Model model,
			@RequestParam("starttime")Date starttime,
			@RequestParam("endtime")Date endtime,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="5")int size) {
		
		PageInfo<Notice> notices = noticeService.searchByDatePages(starttime,endtime,pageNum,size);
		model.addAttribute("notices", notices);
		
		
		session.setAttribute("searchpage", true);
		session.setAttribute("starttime", starttime);
		session.setAttribute("endtime", endtime);
		return "admin/governnotice";
	}
	
	//用户查看所有的通知
	@RequestMapping(value="/searchallnotice.html")
	public String searchallnotice(
			HttpSession session,
			Model model,
			@RequestParam("starttime")Date starttime,
			@RequestParam("endtime")Date endtime,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum, 
			@RequestParam(value="size",defaultValue="20")int size) {
		
		PageInfo<Notice> notices = noticeService.searchByDatePages(starttime,endtime,pageNum,size);
		model.addAttribute("notices", notices);
		session.setAttribute("searchpage", true);
		session.setAttribute("starttime", starttime);
		session.setAttribute("endtime", endtime);
		return "allnotice";
	}
	
	
}
