package br.com.mayki.modeloAPI.Views.Dto;

import java.util.ArrayList;
import java.util.List;

import br.com.mayki.modeloAPI.Models.Entity.Livro;
import br.com.mayki.modeloAPI.Models.Repository.LivroRepository;

public class CategoriaLivroDto {

	private List<LivroDto>livros;

	public CategoriaLivroDto(List<LivroDto> livros) {
		this.livros = livros;
	}

	public List<LivroDto> getLivros() {
		return livros;
	}

	public static CategoriaLivroDto paraListaDto(List<Livro> livrosCategoria, LivroRepository livroRepository) {
		List<LivroDto> convertido = new ArrayList<LivroDto>();
		
		livrosCategoria.forEach((Livro l)->{
			convertido.add(new LivroDto(l.getId(), l.getTitulo(), l.getAutor(), CategoriaDto.paralistaDto(l.getCategoria())));
		});
		
		return new CategoriaLivroDto(convertido);
	}
	
	
	
}
