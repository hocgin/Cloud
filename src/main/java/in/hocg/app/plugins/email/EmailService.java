package in.hocg.app.plugins.email;

import org.apache.commons.mail.HtmlEmail;
import org.nutz.aop.interceptor.async.Async;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.Callback;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Created by hocgin on 16-12-6.
 */
@IocBean
public class EmailService {
	private static final Log log = Logs.get();
	
	@Inject("refer:$ioc")
	protected Ioc ioc;
	
	/**
	 * 邮件发送
	 * @param to 收件地址
	 * @param subject 标题
	 * @param html 发送内容
	 * @return
	 */
	public boolean send(String to, String subject, String html) {
		try {
			HtmlEmail email = ioc.get(HtmlEmail.class);
			email.setSubject(subject);
			email.setHtmlMsg(html);
			email.addTo(to);
			email.buildMimeMessage();
			email.sendMimeMessage();
			return true;
		} catch (Throwable e) {
			log.info("send email fail", e);
			return false;
		}
	}
	
	/**
	 * 同步发送
	 * @param to 收件地址
	 * @param subject 标题
	 * @param html 发送内容
	 * @param callback 回调
	 */
	@Async
	public void sendAsync(String to, String subject, String html, Callback<Boolean> callback) {
		boolean re = this.send(to, subject, html);
		if (callback != null)
			callback.invoke(re);
	}
	
}
