package njit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import njit.model.Notice;
import njit.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//更新通知界面
	@RequestMapping(value="/updateNoticeView",method=RequestMethod.GET)
	public String updateNoticeView(Model model,@RequestParam("nid")Integer nid) {
		Notice notice = noticeService.select(nid);
		model.addAttribute("notice", notice);
		return "admin/edit_notice";
	}
	
	//发布通知
	@RequestMapping(value="/issueNotice",method=RequestMethod.POST)
	public String issueNotice(Notice notice) {
		noticeService.issueNotice(notice);
		return "redirect:/admin/governnotice.html";
	}
	
	//删除对应的通知
	@RequestMapping(value="/deleteNotice",method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("nid")Integer nid) {
		noticeService.delete(nid);
		return "redirect:/admin/governnotice.html";
	}
	
	//批量删除通知
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
	
}
