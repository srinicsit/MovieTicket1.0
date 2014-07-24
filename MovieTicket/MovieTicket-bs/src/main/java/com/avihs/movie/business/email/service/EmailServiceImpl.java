package com.avihs.movie.business.email.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public static final String TO_MAIL_ID = "toMailId";

	public static final String SUBJECT = "subject";

	public static final String FROM_MAIL_ID = "avihs2014@gmail.com";

	public EmailServiceImpl() {

	}

	public void sendMail(final Map model, final String velocityTemplateFile) {
		final String encoding = "utf-8";

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				// message.setTo("srini.csit@gmail.com");
				message.setTo(model.get(TO_MAIL_ID) + "");
				message.setSubject(SUBJECT);
				message.setBcc(FROM_MAIL_ID);
				message.setFrom(FROM_MAIL_ID);

				// String text = VelocityEngineUtils.mergeTemplateIntoString(
				// velocityEngine, encoding,
				// "com/dns/registration-confirmation.vm", model);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, velocityTemplateFile, encoding, model);

				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
	}
}
