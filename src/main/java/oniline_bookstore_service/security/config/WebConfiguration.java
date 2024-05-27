package oniline_bookstore_service.security.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import oniline_bookstore_service.jwt.converter.JwtToUserConverter;
import oniline_bookstore_service.utils.KeyUtils;

@Component
@EnableWebSecurity
@EnableMethodSecurity
//@RequiredArgsConstructor
public class WebConfiguration {

	@Autowired
	JwtToUserConverter jwtToUserConverter;

	@Autowired
	KeyUtils keyUtils;

	@Autowired
	CustomSha256WithSaltPasswordEncoder authenticationProvider;
	private static final String[] WHITE_LIST_URL = { "/api/v1/user/signUp", "/api/v1/user/login","/api/v1/user/verify_contact",
			"/onboarding/verify-contact", "/onboarding/resend-otp","/onboarding/forgot-password","/onboarding/reset-password", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**",
			"/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
			"/swagger-ui/**", "/webjars/**", "/swagger-ui.html" };

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorization) -> authorization.requestMatchers(WHITE_LIST_URL).permitAll()
				.anyRequest().authenticated());

		http.exceptionHandling(exception -> exception.authenticationEntryPoint(new MyAuthenticationEntryPoint()));
		http.csrf(csrf -> csrf.disable());
		http.authenticationProvider(authenticationProvider).cors().disable().httpBasic().disable()
				.oauth2ResourceServer(
						(oauth2) -> oauth2.jwt((jwt) -> jwt.jwtAuthenticationConverter(jwtToUserConverter)))
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(
						(exceptions) -> exceptions.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
								.accessDeniedHandler(new BearerTokenAccessDeniedHandler())).cors(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	@Primary
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyUtils.getAccessTokenPublicKey()).build();
	}

	@Bean
	@Primary
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(keyUtils.getAccessTokenPublicKey()).privateKey(keyUtils.getAccessTokenPrivateKey())
				.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	@Qualifier("jwtRefreshTokenDecoder")
	JwtDecoder jwtRefreshTokenDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyUtils.getRefreshTokenPublicKey()).build();
	}

	@Bean
	@Qualifier("jwtRefreshTokenEncoder")
	JwtEncoder jwtRefreshTokenEncoder() {
		JWK jwk = new RSAKey.Builder(keyUtils.getRefreshTokenPublicKey())
				.privateKey(keyUtils.getRefreshTokenPrivateKey()).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
