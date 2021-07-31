package br.com.mayki.modeloAPI.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.modeloAPI.Models.Entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
