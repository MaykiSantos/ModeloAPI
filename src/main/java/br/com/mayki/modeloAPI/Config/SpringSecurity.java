package br.com.mayki.modeloAPI.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.mayki.modeloAPI.Models.Repository.UsuarioRepository;
import br.com.mayki.modeloAPI.Services.TokenService;
import br.com.mayki.modeloAPI.Services.UsuarioAutenticacao;

@EnableWebSecurity
@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioAutenticacao usuarioAutenticacao;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TokenService tokenService;

	@Override // define as configurações de autenticação
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioAutenticacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override // define as autorizações dos recursos
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/livros").permitAll()
				.antMatchers(HttpMethod.GET, "/livros/**").permitAll()
				.antMatchers(HttpMethod.GET, "/categorias").permitAll()
				.antMatchers(HttpMethod.GET, "/categorias/**").permitAll()
				.antMatchers(HttpMethod.GET, "/donos").permitAll()
				.antMatchers(HttpMethod.GET, "/donos/**").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoTokenFilter(usuarioRepository, tokenService), UsernamePasswordAuthenticationFilter.class);

	}

	@Override // define as configurações de arquivos extaticos(js, css, jpg...)
	public void configure(WebSecurity web) throws Exception {
	}

	@Override
	@Bean(value = "AuthenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

}
