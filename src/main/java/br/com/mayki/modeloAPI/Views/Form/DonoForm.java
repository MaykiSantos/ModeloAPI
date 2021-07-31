package br.com.mayki.modeloAPI.Views.Form;

import javax.validation.constraints.Size;

import br.com.mayki.modeloAPI.Models.Entity.Dono;
import br.com.mayki.modeloAPI.Models.Repository.DonoRepository;

public class DonoForm {
	
	@Size(min = 3, max = 30)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dono paraDono(DonoRepository donoRepository) {
		Dono dono= new Dono(this.nome);
		return donoRepository.save(dono);
	}
	
	

}
