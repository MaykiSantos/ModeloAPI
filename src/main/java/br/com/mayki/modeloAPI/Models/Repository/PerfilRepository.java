package br.com.mayki.modeloAPI.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.modeloAPI.Models.Entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
