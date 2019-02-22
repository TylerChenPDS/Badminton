package njit.web;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import njit.model.User;

@SuppressWarnings("all")
public class AuthInceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String path = "";
		if(handler instanceof  HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			path = requestMapping.value()[0];
			//所有权限控制的url
			List<String> allPermRes = (List<String>) request.getServletContext().getAttribute("allPermRes");
			
			//用户可以访问权限控制的路径
			List<String> canReachPaths = (List<String>) session.getAttribute("session");
			
			User loginUser = (User) session.getAttribute("loginUser");
			
			if(loginUser == null) {
				response.sendRedirect(request.getContextPath() + "/login.html");
			}
			else {
				boolean isAdmin = (boolean) session.getAttribute("isAdmin");
				if(!isAdmin && allPermRes.contains(path)) {//要求判断当前用户有没有权限访问当前的页面
					if(!canReachPaths.contains(path))
						throw new RuntimeException("你没有访问该页面的权限");
				}
			}
			
		}else {
			throw new RuntimeException("404错误");
		}
		return super.preHandle(request, response, handler);
	}
}
