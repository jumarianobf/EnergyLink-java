package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.PagamentoDTO;
import br.com.fiap.gs.entity.ContratoDistribuicao;
import br.com.fiap.gs.entity.Pagamento;
import br.com.fiap.gs.repository.ContratoDistribuicaoRepository;
import br.com.fiap.gs.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

    @Override
    public Pagamento createPagamento(PagamentoDTO request) {
        ContratoDistribuicao contratoDistribuicao = contratoDistribuicaoRepository.findById(request.getIdContrato()
        ).orElseThrow(() -> new RuntimeException("Contrato de Distribuição não encontrado"));

        return pagamentoRepository.save(getPagamento(request, contratoDistribuicao));
    }

    private Pagamento getPagamento(PagamentoDTO request, ContratoDistribuicao contratoDistribuicao) {
        return Pagamento.builder()
                .idContrato(contratoDistribuicao)
                .dataPagamento(request.getDataPagamento())
                .valor(request.getValor())
                .statusPagamento(request.getStatusPagamento())
                .build();
    }

    @Override
    public Pagamento getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (pagamentoRepository.existsById(id)) {
            return pagamentoRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Pagamento> getAllPagamento() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Pagamento updatePagamento(Long id, PagamentoDTO monitoramentoProducaoDTO) {
        Pagamento existingPagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        ContratoDistribuicao contratoDistribuicao = contratoDistribuicaoRepository.findById(monitoramentoProducaoDTO.getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato de Distribuição não encontrado com ID: " + monitoramentoProducaoDTO.getIdContrato()));

        existingPagamento.setIdContrato(contratoDistribuicao);
        existingPagamento.setDataPagamento(monitoramentoProducaoDTO.getDataPagamento());
        existingPagamento.setValor(monitoramentoProducaoDTO.getValor());
        existingPagamento.setStatusPagamento(monitoramentoProducaoDTO.getStatusPagamento());

        return pagamentoRepository.save(existingPagamento);
    }

    @Override
    public void deletePagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
