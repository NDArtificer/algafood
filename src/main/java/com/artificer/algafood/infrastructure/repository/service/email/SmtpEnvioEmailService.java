package com.artificer.algafood.infrastructure.repository.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.artificer.algafood.core.email.EmailProperties;
import com.artificer.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration config;

	@Override
	public void enviar(Message message) {

		try {
			String body = processarTemplate(message);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

			mimeHelper.setFrom(emailProperties.getRemetente());
			mimeHelper.setTo(message.getRecipients().toArray(new String[0]));
			mimeHelper.setSubject(message.getSubject());
			mimeHelper.setText(body, true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o e-mail!", e);
		}
	}

	protected String processarTemplate(Message mesage) {

		try {
			Template template = config.getTemplate(mesage.getBody());

			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mesage.getModels());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail!", e);
		}
	}

}
