package com.arthurzera.social.media.services.mail;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		try {

			System.out.println("Preparing Email");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setText(text);
			message.setSubject(subject);
			System.out.println("Sending Email");
			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("Start sending");
					emailSender.send(message);
					System.out.println("End sending");
				}
			});
			th.start();
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
			String templateArgs) {
		try {
			String text = String.format(template.getText(), templateArgs);
			this.sendSimpleMessage(to, subject, text);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendMessageWithAttachmen(String to, String subject, String text, String pathToAttachment) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);
			emailSender.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

}
