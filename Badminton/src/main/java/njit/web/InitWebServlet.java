package njit.web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@SuppressWarnings("all")
public class InitWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 做两个初始化的工作
	// 1，初始化Spring ico 容器的引用变量

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void init() throws ServletException {
		try {
			// 初始化 ICO容器 applicationContext
			ServletContext context = getServletContext();
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

			// 2，权限初始化
			String packageName = "njit.controller";
			String packagePath = packageName.replaceAll("\\.", "/");
			String packageRealPath = this.getClass().getClassLoader().getResource("/" + packagePath).getPath();
			File file = new File(packageRealPath);
			String[] classFileNames = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".class"))
						return true;
					return false;
				}
			});
			// 构造出class类名的包全名
			List<String> limitAdminpaths = new ArrayList<>();
			List<String> limitUserpaths = new ArrayList<>();
			for (String classFileName : classFileNames) {
				
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				String classAllPackageName = packageName + "." + classFileName;
				Class clazz = Class.forName(classAllPackageName);
				if (!clazz.isAnnotationPresent(AuthClass.class))
					continue;
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(AuthMethod.class) || !method.isAnnotationPresent(RequestMapping.class))
						continue;
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					String type = method.getAnnotation(AuthMethod.class).value();
					if(requestMapping.value().length > 0) {
						if(type.equals("user"))
							limitUserpaths.add(requestMapping.value()[0]);
						else if(type.equals("admin"))
							limitAdminpaths.add(requestMapping.value()[0]);
					}
				}
			}
//			System.err.println(limiteUserpaths);
//			System.err.println(limiteAdminpaths);
			context.setAttribute("limitUserpaths", limitUserpaths);
			context.setAttribute("limitAdminpaths", limitAdminpaths);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
