package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.EntidadeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeBaseRepository extends JpaRepository<EntidadeBase, Long> {
}
