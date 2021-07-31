package br.com.mayki.modeloAPI.Views.Dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.mayki.modeloAPI.Models.Entity.Categoria;

public class CategoriaDto {

	private Long id;
	private String nome;
	
	public CategoriaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static List<CategoriaDto> paralistaDto(List<Categoria> lista) {
		List<CategoriaDto> listaConvertida = new ArrayList<CategoriaDto>();
		
		lista.forEach((Categoria c)->{
			listaConvertida.add(new CategoriaDto(c.getId(), c.getNome()));
		});
		
		return listaConvertida;
	}
	
	public static Page<CategoriaDto> paraPageDto(Page<Categoria> lista) {
		return lista.map(c -> new CategoriaDto(c.getId(), c.getNome()));
	}

	public static CategoriaDto paraDto(Categoria c) {
		return new CategoriaDto(c.getId(), c.getNome());
	}
	
	
}
