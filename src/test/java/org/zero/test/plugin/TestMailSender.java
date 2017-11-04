package org.zero.test.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.zero.boot.util.DateUtil;
import org.zero.test.TestBasic;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 邮件发送示例
 * Attention: 
 * 1.spring.mail.password 项配置的是邮箱安全配置的授权码
 * 2.针对不同的异常信息，注意有些邮件地址或许收不到邮件
 * 3.配置文件中的授权码需使用正确的授权码
 * @date 2017年11月3日 下午7:37:49
 * @author zero
 */
public class TestMailSender extends TestBasic {
	@Autowired
	private JavaMailSender javaMailSender;
	
	String from = "782438007@qq.com";
	String to = "782438007@qq.com";
	/**
	 * 简单文本邮件发送示例
	 */
	@Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("【主题】简单文本邮件");
		message.setText("Hello, this mail from " + from + ", now is " + DateUtil.format(new Date()));
		javaMailSender.send(message);
	}
	
	
	/**
	 * 添加附件发送
	 * @throws MessagingException 
	 */
	@Test
	public void sendMailWithAttachment() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数[true]表明该邮件内容含有附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("【主题】附件邮件");
		helper.setText("Hello, this mail from " + from + " with attachment, now is " + DateUtil.format(new Date()));
		
		FileSystemResource resource = new FileSystemResource(new File("LICENSE"));
		helper.addAttachment("LICENSE", resource);
		
		javaMailSender.send(mimeMessage);
	}
	
	/**
	 * 嵌入静态资源
	 * @throws MessagingException 
	 */
	@Test
	public void sendMailWithPic() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数[true]表明该邮件内容含有附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("【主题】内嵌图片");
		// cid(contentid): 与addInline中的名称一致
		helper.setText("<html><body><h3>Hello, this mail from " + from + " with pic, now is " + DateUtil.format(new Date()) + "</h3><br/><img src=\"cid:icon\" ></body></html>", true);
		
		FileSystemResource file = new FileSystemResource(new File("favicon.png"));
		helper.addInline("icon", file);
		
		javaMailSender.send(mimeMessage);
	}
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	/**
	 * 使用模板
	 * 模板路径默认以 classpath:templates/ 为base路径
	 * @throws MessagingException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	@Test
	public void sendMailWithVelocity() throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数[true]表明该邮件内容含有附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("【主题】模板邮件");

		Map<String, Object> param = new HashMap<>();
		param.put("from", from);
		param.put("datetime", DateUtil.format(new Date()));
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mails/example.html");
		
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, param);
		helper.setText(text, true);
		
		javaMailSender.send(mimeMessage);
	}
	
}
