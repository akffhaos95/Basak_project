package com.basak.app;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserMailSendService {
	@Autowired
	private JavaMailSender mailSender;
	
	private int init() {
		Random ran = new Random();
		int num = ran.nextInt(size);
		return num;
	}
	private int size;
	
	public int getKey(int size) {
		this.size = size;
		return init();
	}
	
	public int mailSendWithUserKey(String email, String memberId, HttpServletRequest request) {
		int key = getKey(10000);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 Basak 입니다.</h2><br><br>" 
				+ "<h3>" + memberId + "님</h3>" + "<p>인증하기 버튼을 누르시면 계정을 사용하실 수 있습니다 : " 
				+ "<a href='http://localhost:8088//member/key?memberId="+ memberId +"&memberKey="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] Basak 인증메일", "UTF-8");
			mail.setText(htmlStr, "UTF-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return key;
	}
}
