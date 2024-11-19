package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.ComunidadeProdutoraDTO;
import br.com.fiap.gs.entity.ComunidadeProdutora;
import br.com.fiap.gs.entity.EntidadeBase;
import br.com.fiap.gs.entity.Localizacao;
import br.com.fiap.gs.entity.TipoEnergiaComunidade;
import br.com.fiap.gs.repository.ComunidadeProdutoraRepostitory;
import br.com.fiap.gs.repository.EntidadeBaseRepository;
import br.com.fiap.gs.repository.LocalizacaoRepository;
import br.com.fiap.gs.repository.TipoEnergiaComunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunidadeProdutoraImpl implements ComunidadeProdutoraService {

    @Autowired
    private ComunidadeProdutoraRepostitory comunidadeProdutoraRepository;

    @Autowired
    private EntidadeBaseRepository entidadeBaseRepository;

    @Autowired
    private TipoEnergiaComunidadeRepository tipoEnergiaComunidadeRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Override
    public ComunidadeProdutora createComunidadeProdutora(ComunidadeProdutoraDTO comunidadeProdutoraDTO) {
        EntidadeBase entidade = entidadeBaseRepository.findById(comunidadeProdutoraDTO.getIdEntidade())
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));

        TipoEnergiaComunidade tipoEnergia = tipoEnergiaComunidadeRepository.findById(comunidadeProdutoraDTO.getIdTipoEnergia())
                .orElseThrow(() -> new RuntimeException("Tipo de energia não encontrado"));

        Localizacao localizacao = localizacaoRepository.findById(comunidadeProdutoraDTO.getIdLocalizacao())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        ComunidadeProdutora comunidadeProdutora = getComunidadeProdutora(comunidadeProdutoraDTO, entidade, tipoEnergia, localizacao);

        return comunidadeProdutoraRepository.save(comunidadeProdutora);
    }

    private ComunidadeProdutora getComunidadeProdutora(ComunidadeProdutoraDTO comunidadeProdutoraDTO, EntidadeBase entidade, TipoEnergiaComunidade tipoEnergia, Localizacao localizacao) {
        return ComunidadeProdutora.builder()
                .idEntidade(entidade)
                .idTipoEnergia(tipoEnergia)
                .idLocalizacao(localizacao)
                .capacidadeProducao(comunidadeProdutoraDTO.getCapacidadeProducao())
                .build();
    }

    @Override
    public ComunidadeProdutora getById(Long id) throws ChangeSetPersister.NotFoundException {
        return comunidadeProdutoraRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<ComunidadeProdutora> getAllComunidadesProdutoras() {
        return comunidadeProdutoraRepository.findAll();
    }

    @Override
    public ComunidadeProdutora updateComunidadeProdutora(Long id, ComunidadeProdutoraDTO comunidadeProdutoraDTO) {
        ComunidadeProdutora existingComunidadeProdutora = comunidadeProdutoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunidade não encontrada"));

        EntidadeBase entidade = entidadeBaseRepository.findById(comunidadeProdutoraDTO.getIdEntidade())
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));

        TipoEnergiaComunidade tipoEnergia = tipoEnergiaComunidadeRepository.findById(comunidadeProdutoraDTO.getIdTipoEnergia())
                .orElseThrow(() -> new RuntimeException("Tipo de energia não encontrado"));

        Localizacao localizacao = localizacaoRepository.findById(comunidadeProdutoraDTO.getIdLocalizacao())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        existingComunidadeProdutora.setIdEntidade(entidade);
        existingComunidadeProdutora.setIdTipoEnergia(tipoEnergia);
        existingComunidadeProdutora.setIdLocalizacao(localizacao);
        existingComunidadeProdutora.setCapacidadeProducao(comunidadeProdutoraDTO.getCapacidadeProducao());

        return comunidadeProdutoraRepository.save(existingComunidadeProdutora);
    }

    @Override
    public void deleteComunidadeProdutora(Long id) {
        comunidadeProdutoraRepository.deleteById(id);
    }
}
