package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.MonitoramentoEnergiaDTO;
import br.com.fiap.gs.entity.MonitoramentoEnergia;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MonitoramentoEnergiaService {

    MonitoramentoEnergia createMonitoramentoEnergia(MonitoramentoEnergiaDTO request);


    MonitoramentoEnergia getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<MonitoramentoEnergia> getAllMonitoramentoEnergia();

    MonitoramentoEnergia updateMonitoramentoEnergia(Long id, MonitoramentoEnergiaDTO monitoramentoEnergiaDTO);

    void deleteMonitoramentoEnergia (Long id);
}
