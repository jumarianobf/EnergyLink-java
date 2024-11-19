package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.LocalizacaoDTO;
import br.com.fiap.gs.entity.Cidade;
import br.com.fiap.gs.entity.Localizacao;
import br.com.fiap.gs.repository.CidadeRepository;
import br.com.fiap.gs.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocalizacaoImpl implements LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public Localizacao createLocalizacao(LocalizacaoDTO request) {
        Cidade cidade = cidadeRepository.findById(request.getIdCidade().getIdCidade())
                .orElseThrow(() -> new RuntimeException("Cidade Nao Encontrada: "));

        return localizacaoRepository.save(getLocalizacao(request, cidade));
    }

    private Localizacao getLocalizacao(LocalizacaoDTO request, Cidade cidade) {
        return Localizacao.builder()
                .idCidade(cidade)
                .cep(request.getCep())
                .build();
    }

    @Override
    public Localizacao getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (localizacaoRepository.existsById(id)) {
            return localizacaoRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Localizacao> getAllLocalizacao() {
        return localizacaoRepository.findAll();
    }

    @Override
    public Localizacao updateLocalizacao(Long id, LocalizacaoDTO localizacao) {
        Localizacao existingLocalizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Localização não encontrada"));

        Cidade cidade = cidadeRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));

        existingLocalizacao.setCep(localizacao.getCep());
        existingLocalizacao.setIdCidade(cidade);

        return localizacaoRepository.save(existingLocalizacao);
    }

    @Override
    public void deleteLocalizacao(Long id) {
        localizacaoRepository.deleteById(id);
    }
}
