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
		String htmlStr = "<h2>�ȳ��ϼ��� Basak �Դϴ�.</h2><br><br>" 
				+ "<h3>" + memberId + "��</h3>" + "<p>�����ϱ� ��ư�� �����ø� ������ ����Ͻ� �� �ֽ��ϴ� : " 
				+ "<a href='http://localhost:8088//member/key?memberId="+ memberId +"&memberKey="+key+"'>�����ϱ�</a></p>"
				+ "(Ȥ�� �߸� ���޵� �����̶�� �� �̸����� �����ϼŵ� �˴ϴ�)";
		try {
			mail.setSubject("[��������] Basak ��������", "UTF-8");
			mail.setText(htmlStr, "UTF-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return key;
	}
}
