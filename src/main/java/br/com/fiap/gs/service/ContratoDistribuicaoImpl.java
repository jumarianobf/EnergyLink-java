package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.ContratoDistribuicaoDTO;
import br.com.fiap.gs.entity.ComunidadeProdutora;
import br.com.fiap.gs.entity.ContratoDistribuicao;
import br.com.fiap.gs.entity.FabricaParceira;
import br.com.fiap.gs.repository.ComunidadeProdutoraRepostitory;
import br.com.fiap.gs.repository.ContratoDistribuicaoRepository;
import br.com.fiap.gs.repository.FabricaParceriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContratoDistribuicaoImpl implements ContratoDistribuicaoService{

    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

    @Autowired
    private FabricaParceriaRepository fabricaParceriaRepository;

    @Autowired
    private ComunidadeProdutoraRepostitory comunidadeProdutoraRepostitory;

    @Override
    public ContratoDistribuicao createContratoDistribuicao(ContratoDistribuicaoDTO contratoDistribuicaoDTO) {
        FabricaParceira fabrica = fabricaParceriaRepository.findById(contratoDistribuicaoDTO.getIdFabrica()).orElseThrow(() -> new RuntimeException("Fabrica não encontrada"));
        ComunidadeProdutora comunidade = comunidadeProdutoraRepostitory.findById(contratoDistribuicaoDTO.getIdComunidade()).orElseThrow(() -> new RuntimeException("Comunidade não encontrada"));
        return contratoDistribuicaoRepository.save(getContratoDistribuicao(contratoDistribuicaoDTO, fabrica, comunidade));
    }

    private ContratoDistribuicao getContratoDistribuicao(ContratoDistribuicaoDTO contratoDistribuicaoDTO, FabricaParceira fabrica, ComunidadeProdutora comunidade) {
        return ContratoDistribuicao.builder()
                .idComunidade(comunidade)
                .idFabrica(fabrica)
                .dataInicio(contratoDistribuicaoDTO.getDataInicio())
                .dataFim(contratoDistribuicaoDTO.getDataFim())
                .precoKwh(BigDecimal.valueOf(contratoDistribuicaoDTO.getPrecoKwh()))
                .statusContrato(contratoDistribuicaoDTO.getStatusContrato())
                .build();
    }

    @Override
    public ContratoDistribuicao getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (contratoDistribuicaoRepository.existsById(id)) {
            return contratoDistribuicaoRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<ContratoDistribuicao> getAllContratoDistribuicao() {
            return contratoDistribuicaoRepository.findAll();
    }

    @Override
    public ContratoDistribuicao updateContratoDistribuicao(Long id, ContratoDistribuicaoDTO contratoDistribuicaoDTO) {
        ContratoDistribuicao existingContratoDistribuicao = contratoDistribuicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        FabricaParceira fabrica = fabricaParceriaRepository.findById(contratoDistribuicaoDTO.getIdFabrica())
                .orElseThrow(() -> new RuntimeException("Fabrica não encontrada"));

        ComunidadeProdutora comunidade = comunidadeProdutoraRepostitory.findById(contratoDistribuicaoDTO.getIdComunidade())
                .orElseThrow(() -> new RuntimeException("Comunidade não encontrada"));

        existingContratoDistribuicao.setIdFabrica(fabrica);
        existingContratoDistribuicao.setIdComunidade(comunidade);
        existingContratoDistribuicao.setDataInicio(contratoDistribuicaoDTO.getDataInicio());
        existingContratoDistribuicao.setDataFim(contratoDistribuicaoDTO.getDataFim());
        existingContratoDistribuicao.setPrecoKwh(BigDecimal.valueOf(contratoDistribuicaoDTO.getPrecoKwh()));
        existingContratoDistribuicao.setStatusContrato(contratoDistribuicaoDTO.getStatusContrato());

        return contratoDistribuicaoRepository.save(existingContratoDistribuicao);
    }

    @Override
    public void deleteContratoDistribuicao(Long id) {
        contratoDistribuicaoRepository.deleteById(id);
    }
}
