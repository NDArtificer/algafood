package com.artificer.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artificer.algafood.domain.service.EnvioEmailService;
import com.artificer.algafood.infrastructure.repository.service.email.MockEnvioEmailService;
import com.artificer.algafood.infrastructure.repository.service.email.SandboxEnvioEmailService;
import com.artificer.algafood.infrastructure.repository.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getImpl()) {
		case MOCK:
			return new MockEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandboxEnvioEmailService();
		default:
			return null;
		}
	}

}
