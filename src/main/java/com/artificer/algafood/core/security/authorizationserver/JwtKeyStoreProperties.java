package com.artificer.algafood.core.security.authorizationserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

	@NotNull
	private Resource jksLocation;

	@NotBlank
	private String path;

	@NotBlank
	private String password;

	@NotBlank
	private String keypairAlias;

}
