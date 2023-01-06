package com.artificer.algafood.core.security.authorizationserver;

import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.artificer.algafood.domain.repository.UsuarioRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		return http.formLogin(Customizer.withDefaults()).build();

	}

	@Bean
	public ProviderSettings providerSettings(AlgafoodSecurityProperties properties) {
		return ProviderSettings.builder().issuer(properties.getProviderUrl()).build();
	}

	@Bean
	public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
		RegisteredClient algafoodBackend = RegisteredClient.withId("1")
				.clientId("algafood-backend")
				.clientSecret(passwordEncoder.encode("123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope("READ")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(30)).build())
				.build();
		
		RegisteredClient algafoodweb = RegisteredClient.withId("2")
				.clientId("algafood-web")
				.clientSecret(passwordEncoder.encode("123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(30))
						.reuseRefreshTokens(false)
						.refreshTokenTimeToLive(Duration.ofDays(1))
						.build())
				.redirectUri("http://127.0.0.1:8080/authorized")
				.redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html")
				.clientSettings(ClientSettings.builder()
						.requireAuthorizationConsent(true)
						.build())
				.build();		
		
		RegisteredClient foodAnalytics = RegisteredClient.withId("3")
				.clientId("foodanalytics")
				.clientSecret(passwordEncoder.encode("123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(30)).build())
				.redirectUri("http://www.foodanalytics.local:8082")
				.clientSettings(ClientSettings.builder()
						.requireAuthorizationConsent(false)
						.build())
				.build();		
		
		
		return new InMemoryRegisteredClientRepository(Arrays.asList(algafoodBackend, algafoodweb, foodAnalytics));
	}

	@Bean
	public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties properties) throws Exception {
		var keyStorePass = properties.getPassword().toCharArray();
		String keyPairAlias = properties.getKeypairAlias();
		var jksLocation = properties.getJksLocation();
		var inputStream = jksLocation.getInputStream();
		var keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, keyStorePass);

		var rsaKey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);
		var jwkSet = new ImmutableJWKSet<>(new JWKSet(rsaKey));

		return jwkSet;
	}

	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UsuarioRepository repository){
		return context ->{
			var authentication = context.getPrincipal();
			if(authentication.getPrincipal() instanceof User) {
				User user = (User) authentication.getPrincipal();
				var usuario = repository.findByEmail(user.getUsername()).orElseThrow();
				Set<String> authorities = new HashSet<>();
				for(GrantedAuthority authority: user.getAuthorities()) {
					authorities.add(authority.getAuthority());
				}
				context.getClaims().claim("usuario_id", usuario.getId().toString());
				context.getClaims().claim("authorities", authorities);
			}
		};
	}
	
}
