package br.com.mayki.modeloAPI.Models.Repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mayki.modeloAPI.Models.Entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Page<Livro> findByCategoria_id(Long idCategoria, Pageable pageable);

	Page<Livro> findByTitulo(Example<Livro> of, Pageable pageable);


}
