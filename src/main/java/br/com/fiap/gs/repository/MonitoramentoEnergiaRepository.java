package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.MonitoramentoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoramentoEnergiaRepository extends JpaRepository<MonitoramentoEnergia, Long> {
}
