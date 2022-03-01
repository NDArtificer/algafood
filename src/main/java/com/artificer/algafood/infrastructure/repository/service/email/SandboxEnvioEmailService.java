package com.artificer.algafood.infrastructure.repository.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.artificer.algafood.core.email.EmailProperties;
import com.artificer.algafood.core.email.EmailSandboxProperties;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private EmailSandboxProperties sandboxProperties;

	@Autowired
	private EmailTemplateProcessor templateProcessor;

	@Override
	public void enviar(Message message) {

		try {
			String body = templateProcessor.processarTemplate(message);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

			mimeHelper.setFrom(emailProperties.getRemetente());
			mimeHelper.setTo(sandboxProperties.getDestinatario());
			mimeHelper.setSubject(message.getSubject());
			mimeHelper.setText(body, true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o e-mail!", e);
		}

	}

}
