package com.arthurzera.social.media.services.mail;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {
	void sendSimpleMessage(String to, String subject, String text);
	void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,String templateArgs);
	void sendMessageWithAttachmen(String to, String subject, String text, String pathToAttachment);
}
