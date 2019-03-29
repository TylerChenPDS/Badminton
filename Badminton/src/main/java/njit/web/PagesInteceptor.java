package njit.web;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import njit.model.User;

public class PagesInteceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(handler instanceof  HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			String path = requestMapping.value()[0];
			List<String> limitUserpaths = (List<String>) request.getServletContext().getAttribute("limitUserpaths");
			List<String> limitAdminpaths = (List<String>) request.getServletContext().getAttribute("limitAdminpaths");
			if(limitUserpaths.contains(path)) {
				User user = (User) session.getAttribute("logineduser");
				if(user == null) {
					response.sendRedirect(request.getContextPath() + "/login.html");
					return false;
				}
					
					
			}else if(limitAdminpaths.contains(path)) {
				User user = (User) session.getAttribute("administrator");
				if(user == null) {
					response.sendRedirect(request.getContextPath() + "/login.html");
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
		
	}
	
}
