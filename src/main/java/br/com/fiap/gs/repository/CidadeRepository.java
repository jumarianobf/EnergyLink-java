package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
