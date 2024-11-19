package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}
