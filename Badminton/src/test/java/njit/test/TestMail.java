package njit.test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
public class TestMail {

	private static String asdsa;

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JavaMailSender javaMailSender = ctx.getBean(JavaMailSender.class);
		SimpleMailMessage message = new SimpleMailMessage();
		// 发件人的邮箱地址
		message.setFrom(asdsa);
		// 收件人的邮箱地址
		message.setTo("1969922921@qq.com");
		// 邮件主题
		message.setSubject("验证邮箱提示");
		// 邮件内容
		message.setText("你好！你正在对微信小程程序羽毛球场馆预定系统进行邮箱绑定，请点击http://localhost:8080/Badminton/weixin/validateEmail进行验证。如非本人操作请忽略！！");
		// 发送邮件
		javaMailSender.send(message);
	}

}
