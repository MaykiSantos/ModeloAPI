package br.com.mayki.modeloAPI.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.modeloAPI.Models.Entity.Categoria;
import br.com.mayki.modeloAPI.Models.Entity.Livro;
import br.com.mayki.modeloAPI.Models.Repository.CategoriaRepository;
import br.com.mayki.modeloAPI.Models.Repository.LivroRepository;
import br.com.mayki.modeloAPI.Views.Dto.CategoriaDto;
import br.com.mayki.modeloAPI.Views.Dto.CategoriaLivroDto;
import br.com.mayki.modeloAPI.Views.Dto.LivroDto;
import br.com.mayki.modeloAPI.Views.Form.CategoriaForm;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	LivroRepository livroRepository;

	@GetMapping(produces = "application/json")
	public ResponseEntity<Page<CategoriaDto>> listar(
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
		Page<Categoria> lista = categoriaRepository.findAll(pageable);

		return ResponseEntity.ok(CategoriaDto.paraPageDto(lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.findById(id).get();
		return ResponseEntity.ok(CategoriaDto.paraDto(categoria));
	}

	@GetMapping("/{idCategoria}/livros")
	public ResponseEntity<Page<LivroDto>> buscarPorCategoria(@PathVariable Long idCategoria,
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {

		Page<Livro> livrosCategoria = livroRepository.findByCategoria_id(idCategoria, pageable);

		return ResponseEntity.ok(LivroDto.paraPageDto(livrosCategoria));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> adicionar(@RequestBody CategoriaForm categoriaForm, UriComponentsBuilder uriComponents) {
		Categoria categoria = categoriaForm.paraCategoria(categoriaRepository);
		URI uri = uriComponents.path("/categorias/{id}").build(categoria.getId());
		return ResponseEntity.created(uri).body(CategoriaDto.paraDto(categoria));
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Categoria categoria = categoriaRepository.findById(id).get();

		if (categoria.getLivrocategoria().size() != 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O recurso não pode ser apagado pois está em uso");
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
