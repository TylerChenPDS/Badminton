package njit.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <b>自定义注解表示对应方法需要进行权限控制</b><br>
 * <b>value="admin" 表示需要管理员登陆才能访问的资源<br></b>
 * <b>value="user" 表示需要用户登陆才能访问的资源</b>
 * @author user
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
	public String value() default "";
}
