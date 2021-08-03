package br.com.mayki.modeloAPI.Controller;

import java.net.URI;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.modeloAPI.Models.Entity.Dono;
import br.com.mayki.modeloAPI.Models.Repository.DonoRepository;
import br.com.mayki.modeloAPI.Views.Dto.DonoDto;
import br.com.mayki.modeloAPI.Views.Form.DonoForm;

@RestController
@RequestMapping("/donos")
public class DonoController {

	@Autowired
	DonoRepository donoRepository;

	@GetMapping
	public ResponseEntity<Page<DonoDto>> listar(
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
		Page<Dono> lista = donoRepository.findAll(pageable);
		return ResponseEntity.ok(DonoDto.paraPageDto(lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DonoDto> buscar(@PathVariable Long id) {
		Dono dono = donoRepository.findById(id).get();
		return ResponseEntity.ok(DonoDto.paraDto(dono));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionar(@RequestBody DonoForm donoForm, UriComponentsBuilder uriComponents) {
		Dono dono = donoForm.paraDono(donoRepository);
		URI uri = uriComponents.path("/dono/{id}").build(dono.getId());

		return ResponseEntity.created(uri).body(DonoDto.paraDto(dono));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			Dono dono = donoRepository.findById(id).get();
			donoRepository.delete(dono);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
