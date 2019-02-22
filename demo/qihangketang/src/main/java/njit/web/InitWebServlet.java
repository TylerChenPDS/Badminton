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

import njit.service.ResourceService;

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
			List<String> paths = new ArrayList<>();
			for (String classFileName : classFileNames) {
				
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				String classAllPackageName = packageName + "." + classFileName;
				Class clazz = Class.forName(classAllPackageName);
				if (!clazz.isAnnotationPresent(AuthClass.class))
					continue;
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(AuthMethod.class))
						continue;
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					paths.add(requestMapping.value()[0]);
				}
			}
			
			//将得到的paths放到数据库中
			ResourceService resourceService = (ResourceService) applicationContext.getBean("resourceService");
			resourceService.initPath(paths);
			
			context.setAttribute("allPermRes", paths);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
