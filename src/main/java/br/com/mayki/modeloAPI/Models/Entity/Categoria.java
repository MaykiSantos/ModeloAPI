package br.com.mayki.modeloAPI.Models.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "Categoria")
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Livro> livro;
	
	public Categoria() {}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivrocategoria() {
		return livro;
	}

	public void setLivrocategoria(List<Livro> livro) {
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
