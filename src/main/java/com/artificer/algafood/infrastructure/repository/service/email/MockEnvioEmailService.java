package com.artificer.algafood.infrastructure.repository.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.artificer.algafood.core.email.EmailProperties;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEnvioEmailService extends SmtpEnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration config;

	@Override
	public void enviar(Message message) {

		String body = processarTemplate(message);

		log.info("Enviando E-mail para o {}\n{} ", message.getRecipients(), body);

	}


}
