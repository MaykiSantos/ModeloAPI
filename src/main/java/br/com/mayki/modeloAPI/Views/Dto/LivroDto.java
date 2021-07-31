package br.com.mayki.modeloAPI.Views.Dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.mayki.modeloAPI.Models.Entity.Livro;

public class LivroDto {
	
	private Long id;
	private String titulo;
	private String autor;
	private List<CategoriaDto> categoria;
	
	public LivroDto(Long id, String titulo, String autor, List<CategoriaDto> categoria) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public List<CategoriaDto> getCategoria() {
		return categoria;
	}

	public static Page<LivroDto> paraPageDto(Page<Livro> lista) {
		
		return lista.map(item -> {
			List<CategoriaDto> cat = CategoriaDto.paralistaDto(item.getCategoria());
			return new LivroDto(item.getId(), item.getTitulo(), item.getAutor(), cat);
		});
	}

	public static LivroDto paraDto(Livro l) {
		return new LivroDto(l.getId(), l.getTitulo(), l.getAutor(), CategoriaDto.paralistaDto(l.getCategoria()));
	}

}
