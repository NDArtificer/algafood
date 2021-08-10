package com.artificer.algafood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("algafood.email.sandbox")
public class EmailSandboxProperties {

	private String destinatario;
}
