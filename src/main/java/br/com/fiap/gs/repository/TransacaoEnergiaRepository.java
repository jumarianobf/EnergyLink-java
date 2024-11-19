package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.TransacaoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoEnergiaRepository extends JpaRepository<TransacaoEnergia, Long> {
}
