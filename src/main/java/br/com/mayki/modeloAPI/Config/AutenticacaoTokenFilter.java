package br.com.mayki.modeloAPI.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mayki.modeloAPI.Models.Entity.Usuario;
import br.com.mayki.modeloAPI.Models.Repository.UsuarioRepository;
import br.com.mayki.modeloAPI.Services.TokenService;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private UsuarioRepository usuarioRepository;
	private TokenService tokenService;
	
	public AutenticacaoTokenFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperaToken(request);

		boolean tokenValido = tokenService.validaToken(token);
		
		if(tokenValido) { //valida usuario dentro do spring
			autenticarCliente(token);
			
		}

		filterChain.doFilter(request, response);

	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
	}


	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
