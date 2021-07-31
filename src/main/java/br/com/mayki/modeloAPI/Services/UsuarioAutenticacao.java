package br.com.mayki.modeloAPI.Services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mayki.modeloAPI.Models.Entity.Usuario;
import br.com.mayki.modeloAPI.Models.Repository.UsuarioRepository;

@Service
public class UsuarioAutenticacao implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			Usuario usuario = usuarioRepository.findByEmail(username).get();
			return usuario;
		}catch (NoSuchElementException  e) {
			throw new UsernameNotFoundException("nome do usuario Invalido");
		}
	}

}
