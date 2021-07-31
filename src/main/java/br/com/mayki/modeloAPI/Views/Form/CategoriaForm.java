package br.com.mayki.modeloAPI.Views.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mayki.modeloAPI.Models.Entity.Categoria;
import br.com.mayki.modeloAPI.Models.Repository.CategoriaRepository;

public class CategoriaForm {

	@Size(min = 4, max = 20) @NotNull
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria paraCategoria(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(this.nome); 
		return categoriaRepository.save(categoria);
	}
	
	
	
}
