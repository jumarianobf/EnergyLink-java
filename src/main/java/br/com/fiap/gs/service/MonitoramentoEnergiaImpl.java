package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.MonitoramentoEnergiaDTO;
import br.com.fiap.gs.entity.EntidadeBase;
import br.com.fiap.gs.entity.MonitoramentoEnergia;
import br.com.fiap.gs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MonitoramentoEnergiaImpl implements MonitoramentoEnergiaService {

    @Autowired
    private MonitoramentoEnergiaRepository monitoramentoEnergiaRepository;

    @Autowired
    private EntidadeBaseRepository fabricaParceriaRepository;

    @Override
    public MonitoramentoEnergia createMonitoramentoEnergia(MonitoramentoEnergiaDTO request) {
        EntidadeBase entidadeBase = fabricaParceriaRepository.findById(request.getIdEntidade()).orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
        return monitoramentoEnergiaRepository.save(getMonitoramentoEnergia(request, entidadeBase));
    }

    private MonitoramentoEnergia getMonitoramentoEnergia(MonitoramentoEnergiaDTO request, EntidadeBase entidadeBase) {
        return MonitoramentoEnergia.builder()
                .idEntidade(entidadeBase)
                .dataMonitoramento(request.getDataMonitoramento())
                .energia(request.getEnergia())
                .tipoMonitoramento(request.getTipoMonitoramento())
                .build();
    }

    @Override
    public MonitoramentoEnergia getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (monitoramentoEnergiaRepository.existsById(id)) {
            return monitoramentoEnergiaRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<MonitoramentoEnergia> getAllMonitoramentoEnergia() {
        return monitoramentoEnergiaRepository.findAll();
    }

    @Override
    public MonitoramentoEnergia updateMonitoramentoEnergia(Long id, MonitoramentoEnergiaDTO monitoramentoEnergiaDTO) {
        MonitoramentoEnergia existingMonitoramentoEnergia = monitoramentoEnergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MonitoramentoConsumo não encontrado"));

        EntidadeBase entidadeBase = fabricaParceriaRepository.findById(monitoramentoEnergiaDTO.getIdEntidade())
                .orElseThrow(() -> new NoSuchElementException("Entidade não encontrada com ID: " + monitoramentoEnergiaDTO.getIdEntidade()));

        existingMonitoramentoEnergia.setIdEntidade(entidadeBase);
        existingMonitoramentoEnergia.setDataMonitoramento(monitoramentoEnergiaDTO.getDataMonitoramento());
        existingMonitoramentoEnergia.setEnergia(monitoramentoEnergiaDTO.getEnergia());
        existingMonitoramentoEnergia.setTipoMonitoramento(monitoramentoEnergiaDTO.getTipoMonitoramento());

        return monitoramentoEnergiaRepository.save(existingMonitoramentoEnergia);

    }

    @Override
    public void deleteMonitoramentoEnergia(Long id) {
        monitoramentoEnergiaRepository.deleteById(id);
    }
}
