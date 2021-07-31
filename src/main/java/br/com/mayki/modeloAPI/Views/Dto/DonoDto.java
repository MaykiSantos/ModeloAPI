package br.com.mayki.modeloAPI.Views.Dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.mayki.modeloAPI.Models.Entity.Dono;

public class DonoDto {

	private Long id;
	private String nome;

	public DonoDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static List<DonoDto> paraListaDto(List<Dono> lista) {
		List<DonoDto> listaConvertida = new ArrayList<DonoDto>();
		
		lista.forEach((Dono d)->{
			listaConvertida.add(new DonoDto(d.getId(), d.getNome()));
		});
		
		return listaConvertida;
	}

	public static DonoDto paraDto(Dono d) {
		return new DonoDto(d.getId(), d.getNome());
	}

	public static Page<DonoDto> paraPageDto(Page<Dono> lista) {
		return lista.map(d -> new DonoDto(d.getId(), d.getNome()));
	}

}
