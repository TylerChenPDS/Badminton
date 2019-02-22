package njit.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>自定义注解 这个注解就是标记，对应类是需要权限控制的</b>
 * @author user
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)  
public @interface AuthClass {
	public String value() default "";
}
