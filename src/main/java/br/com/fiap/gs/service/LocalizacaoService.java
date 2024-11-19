package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.LocalizacaoDTO;
import br.com.fiap.gs.entity.Localizacao;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface LocalizacaoService {

    Localizacao createLocalizacao(LocalizacaoDTO localizacao);

    Localizacao getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<Localizacao> getAllLocalizacao();

    Localizacao updateLocalizacao(Long id, LocalizacaoDTO localizacao);

    void deleteLocalizacao (Long id);


}
