package br.com.mayki.modeloAPI.Views.Form;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.mayki.modeloAPI.Models.Entity.Categoria;
import br.com.mayki.modeloAPI.Models.Entity.Dono;
import br.com.mayki.modeloAPI.Models.Entity.Livro;
import br.com.mayki.modeloAPI.Models.Repository.CategoriaRepository;
import br.com.mayki.modeloAPI.Models.Repository.DonoRepository;
import br.com.mayki.modeloAPI.Models.Repository.LivroRepository;
import br.com.mayki.modeloAPI.Utilitario.Exceptions.ExceptionMayki;

public class LivroForm {

	@NotNull @Size(min = 6, max = 40)
	private String titulo;
	@NotNull @Size(min = 6, max = 90)
	private String autor;
	@NotNull
	private List<Long> categoria;
	@NotNull @Positive
	private Long donoId;

	public LivroForm(String titulo, String autor, List<Long> categoria, Long donoId) {
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.donoId = donoId;
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

	public List<Long> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Long> categoria) {
		this.categoria = categoria;
	}

	public long getDonoId() {
		return donoId;
	}

	public void setDonoId(Long donoId) {
		this.donoId = donoId;
	}

	public Livro paraLivro(LivroRepository livroRepository, CategoriaRepository categoriaRepository,
			DonoRepository donoRepository) throws ExceptionMayki {

		List<Categoria> categorias = verificaCategoria(this.categoria, categoriaRepository);
		Dono dono = verificaDono(donoId, donoRepository);

		Livro livro = new Livro(this.titulo, this.getAutor(), dono, categorias);
		return livroRepository.save(livro);
	}

	public void atualiza(Livro l, CategoriaRepository categoriaRepository, DonoRepository donoRepository)
			throws ExceptionMayki {
		List<Categoria> categorias = verificaCategoria(this.categoria, categoriaRepository);
		Dono dono = verificaDono(donoId, donoRepository);

		l.setAutor(autor);
		l.setDono(dono);
		l.setTitulo(titulo);
		l.setCategoria(categorias);
	}

	private List<Categoria> verificaCategoria(List<Long> lista, CategoriaRepository categoriaRepository)
			throws ExceptionMayki {
		List<Categoria> categorias = new ArrayList<Categoria>();

		try {
			this.categoria.forEach((Long c) -> {
				categorias.add(categoriaRepository.findById(c).get());
			});
		} catch (NoSuchElementException e) {
			throw new ExceptionMayki("Erro ao atualizar livro. Categoria informada invalida.");
		}
		return categorias;
	}

	private Dono verificaDono(Long idDono, DonoRepository donoRepository) throws ExceptionMayki {
		try {
			Dono dono = donoRepository.findById(donoId).get();
			return dono;
		} catch (NoSuchElementException e) {
			throw new ExceptionMayki("Erro ao atualizar livro. Dono informado não existe.");
		}
	}

}
