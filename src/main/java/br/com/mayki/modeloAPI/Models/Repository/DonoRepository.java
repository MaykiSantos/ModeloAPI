package br.com.mayki.modeloAPI.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.modeloAPI.Models.Entity.Dono;

public interface DonoRepository extends JpaRepository<Dono, Long> {

}
