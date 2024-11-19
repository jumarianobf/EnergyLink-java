package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
}
