package br.com.mayki.modeloAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mayki.modeloAPI.Services.TokenService;
import br.com.mayki.modeloAPI.Views.Dto.TokenDto;
import br.com.mayki.modeloAPI.Views.Form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form){
		
		try {
			UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha());
			Authentication authentication = authManager.authenticate(dadosLogin);
			//Ler documentação do gerador de token do Spring boot
			//https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/token/package-summary.html
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	

}
