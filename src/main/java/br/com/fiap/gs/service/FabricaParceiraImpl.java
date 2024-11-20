package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.FabricaParceiraDTO;
import br.com.fiap.gs.entity.EntidadeBase;
import br.com.fiap.gs.entity.FabricaParceira;
import br.com.fiap.gs.entity.Localizacao;
import br.com.fiap.gs.repository.EntidadeBaseRepository;
import br.com.fiap.gs.repository.FabricaParceriaRepository;
import br.com.fiap.gs.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FabricaParceiraImpl implements FabricaParceiraService {

    @Autowired
    private FabricaParceriaRepository fabricaParceriaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private EntidadeBaseRepository entidadeBaseRepository;

    @Override
    public FabricaParceira createFabrica(FabricaParceiraDTO fabrica) {
        Localizacao localizacao = localizacaoRepository.findById(fabrica.getIdLocalizacao())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        EntidadeBase entidadeBase = entidadeBaseRepository.findById(fabrica.getIdEntidade())
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));

        return fabricaParceriaRepository.save(getFabricaParceira(fabrica, entidadeBase, localizacao));
    }

    private FabricaParceira getFabricaParceira(FabricaParceiraDTO fabrica, EntidadeBase entidadeBase,Localizacao localizacao) {
        return FabricaParceira.builder()
                .idEntidade(entidadeBase)
                .idLocalizacao(localizacao)
                .demandaEnergiaFabrica(fabrica.getDemandaEnergiaFabrica())
                .build();
    }

    @Override
    public FabricaParceira getById(Long id) throws ChangeSetPersister.NotFoundException {
        if(fabricaParceriaRepository.existsById(id)) {
            return fabricaParceriaRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<FabricaParceira> getAllFabrica() {
        return fabricaParceriaRepository.findAll();
    }

    @Override
    public FabricaParceira updateFabrica(Long id, FabricaParceiraDTO fabrica) {
        FabricaParceira existingFabrica = fabricaParceriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Fabrica não encontrada"));

        Localizacao localizacao = localizacaoRepository.findById(fabrica.getIdLocalizacao())
                .orElseThrow(() -> new NoSuchElementException("Localização não encontrada com ID: " + fabrica.getIdLocalizacao()));

        EntidadeBase entidadeBase = entidadeBaseRepository.findById(fabrica.getIdEntidade())
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + fabrica.getIdEntidade()));

        existingFabrica.setIdEntidade(entidadeBase);
        existingFabrica.setIdLocalizacao(localizacao);
        existingFabrica.setDemandaEnergiaFabrica(fabrica.getDemandaEnergiaFabrica());

        return fabricaParceriaRepository.save(existingFabrica);
    }

    @Override
    public void deleteFabrica(Long id) {
        fabricaParceriaRepository.deleteById(id);
    }
}
