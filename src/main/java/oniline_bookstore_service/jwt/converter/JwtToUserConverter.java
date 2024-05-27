package oniline_bookstore_service.jwt.converter;

import java.util.Collection;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import oniline_bookstore_service.user.model.User;

@Service
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

	@Override
	public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
		User user = new User();
		user.setEmailAddress(jwt.getSubject());
		Collection<String> authorities = jwt.getClaimAsStringList("roles");
		Collection<? extends GrantedAuthority> grantedAuthorities = authorities.stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
		return new UsernamePasswordAuthenticationToken(user, jwt, grantedAuthorities);
	}
}