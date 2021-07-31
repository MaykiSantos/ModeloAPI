package br.com.mayki.modeloAPI.Models.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.mayki.modeloAPI.Views.Dto.CategoriaDto;

@Entity(name = "Livro")
@Table(name = "livros")
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String autor;
	@ManyToOne(fetch = FetchType.LAZY)
	private Dono dono;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Categoria> categoria;
	
	public Livro() {}
	
	public Livro(String titulo, String autor, Dono dono, List<Categoria> categoria) {
		this.titulo = titulo;
		this.autor = autor;
		this.dono = dono;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> livrocategoria) {
		this.categoria = livrocategoria;
	}
	
	
}
