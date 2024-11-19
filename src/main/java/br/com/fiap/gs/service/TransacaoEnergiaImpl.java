package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.TransacaoEnergiaDTO;
import br.com.fiap.gs.entity.ContratoDistribuicao;
import br.com.fiap.gs.entity.TransacaoEnergia;
import br.com.fiap.gs.repository.ContratoDistribuicaoRepository;
import br.com.fiap.gs.repository.TransacaoEnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoEnergiaImpl implements TransacaoEnergiaService {

    @Autowired
    private TransacaoEnergiaRepository transacaoEnergiaRepository;

    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

    @Override
    public TransacaoEnergia createTransacaoEnergia(TransacaoEnergiaDTO request) {
        ContratoDistribuicao contratoDistribuicao = contratoDistribuicaoRepository.findById(request.getIdContrato()
        ).orElseThrow(() -> new RuntimeException("Contrato de Distribuição não encontrado"));

        return transacaoEnergiaRepository.save(getPagamento(request, contratoDistribuicao));
    }

    private TransacaoEnergia getPagamento(TransacaoEnergiaDTO request, ContratoDistribuicao contratoDistribuicao) {
        return TransacaoEnergia.builder()
                .idContrato(contratoDistribuicao)
                .dataTransacao(request.getDataTransacao())
                .custoTotal(request.getCustoTotal())
                .quantidadeEnergia(request.getQuantidadeEnergia())
                .statusTransacao(request.getStatusTransacao())
                .build();
    }

    @Override
    public TransacaoEnergia getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (transacaoEnergiaRepository.existsById(id)) {
            return transacaoEnergiaRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<TransacaoEnergia> getAllTransacaoEnergia() {
        return transacaoEnergiaRepository.findAll();
    }

    @Override
    public TransacaoEnergia updateTransacaoEnergia(Long id, TransacaoEnergiaDTO transacaoEnergiaDTO) {
        TransacaoEnergia existingTransacao = transacaoEnergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacao não encontrado"));

        ContratoDistribuicao contratoDistribuicao = contratoDistribuicaoRepository.findById(transacaoEnergiaDTO.getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato de Distribuição não encontrado com ID: " + transacaoEnergiaDTO.getIdContrato()));

        existingTransacao.setIdContrato(contratoDistribuicao);
        existingTransacao.setDataTransacao(transacaoEnergiaDTO.getDataTransacao());
        existingTransacao.setCustoTotal(transacaoEnergiaDTO.getCustoTotal());
        existingTransacao.setQuantidadeEnergia(transacaoEnergiaDTO.getQuantidadeEnergia());
        existingTransacao.setStatusTransacao(transacaoEnergiaDTO.getStatusTransacao());

        return transacaoEnergiaRepository.save(existingTransacao);
    }

    @Override
    public void deleteTransacaoEnergia(Long id) {
        transacaoEnergiaRepository.deleteById(id);
    }
}
