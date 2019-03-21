package njit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value= {"/","","/index","/index.html"})
	public String index() {
		return "index";
	}
	@RequestMapping(method=RequestMethod.GET,path= {"/login.html","/login"})
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/register.html")
	public String registerView() {
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/findpassword.html")
	public String findPassword() {
		return "findpassword";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/checknotice.html")
	public String checkNotice() {
		return "checknotice";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/checkstadium.html")
	public String checkStadium() {
		return "checkstadium";
	}
	
}
