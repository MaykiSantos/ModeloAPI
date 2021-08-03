package br.com.mayki.modeloAPI.Controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.modeloAPI.Models.Entity.Livro;
import br.com.mayki.modeloAPI.Models.Repository.CategoriaRepository;
import br.com.mayki.modeloAPI.Models.Repository.DonoRepository;
import br.com.mayki.modeloAPI.Models.Repository.LivroRepository;
import br.com.mayki.modeloAPI.Utilitario.Exceptions.ExceptionMayki;
import br.com.mayki.modeloAPI.Views.Dto.LivroDto;
import br.com.mayki.modeloAPI.Views.Form.LivroForm;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroRepository livroRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	DonoRepository donoRepository;

	@GetMapping(produces = "application/json")
	public ResponseEntity<Page<LivroDto>> buscar(
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String search) {
		Page<Livro> lista = null;

		if (search == null) { // realiza consulta normalmente caso o search não tenha sido passado na url
			lista = livroRepository.findAll(pageable);

		} else { // faz consulta usando a pesquisa
			Livro livro = new Livro(); // instancia um objeto do tipo que deseja buscar
			livro.setTitulo(search); // define um dos atributos que servirá de comparação

			ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase(true)
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // define as condições da pesquisa

			lista = livroRepository.findAll(Example.of(livro, matcher), pageable); // realiza a consulta
		}

		return ResponseEntity.ok(LivroDto.paraPageDto(lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> buscar(@PathVariable Long id) {
		Livro livro = livroRepository.findById(id).get();

		return ResponseEntity.ok(LivroDto.paraDto(livro));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionar(@RequestBody @Valid LivroForm livroForm, UriComponentsBuilder uriComponents)
			throws ExceptionMayki {
		Livro livro = livroForm.paraLivro(livroRepository, categoriaRepository, donoRepository);
		URI uri = uriComponents.path("/livro/{id}").build(livro.getId());
		return ResponseEntity.created(uri).body(LivroDto.paraDto(livro));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LivroDto> editar(@PathVariable Long id, @RequestBody LivroForm livroForm)
			throws ExceptionMayki {
		Livro livro = livroRepository.findById(id).get();
		livroForm.atualiza(livro, categoriaRepository, donoRepository);

		return ResponseEntity.ok(LivroDto.paraDto(livro));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		livroRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
