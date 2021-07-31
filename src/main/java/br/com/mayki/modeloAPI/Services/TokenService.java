package br.com.mayki.modeloAPI.Services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.mayki.modeloAPI.Models.Entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private Long tempoExpiracao;
	
	@Value("${forum.jwt.secret}")
	private  String senhaAplicacao;
	
	
	
	public String gerarToken(Authentication authentication) {
		
		Usuario usuario = (Usuario)authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + tempoExpiracao);
		SecretKey keyAplicacao = Keys.hmacShaKeyFor(senhaAplicacao.getBytes(StandardCharsets.UTF_8));
		
		
		String token = Jwts.builder()
				.setIssuer("APIModelo")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(keyAplicacao).compact();
		
		return token;
	}

	public boolean validaToken(String token) {
		try {
			SecretKey keyAplicacao = Keys.hmacShaKeyFor(senhaAplicacao.getBytes(StandardCharsets.UTF_8));
			
			Jws<Claims> claimsJws = Jwts.parserBuilder()
			.setSigningKey(keyAplicacao).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		SecretKey keyAplicacao = Keys.hmacShaKeyFor(senhaAplicacao.getBytes(StandardCharsets.UTF_8));
		
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(keyAplicacao).build().parseClaimsJws(token);
		return Long.parseLong(claimsJws.getBody().getSubject());
	}

	
}
