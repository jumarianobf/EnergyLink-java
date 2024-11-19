package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.TipoEnergiaComunidade;
import br.com.fiap.gs.repository.TipoEnergiaComunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEnergiaComunidadeImpl implements TipoEnergiaComunidadeService {

    @Autowired
    private TipoEnergiaComunidadeRepository tipoEnergiaComunidadeRepository;

    @Override
    public TipoEnergiaComunidade createTipoEnergiaComunidade(TipoEnergiaComunidade request) {
        return tipoEnergiaComunidadeRepository.save(request);
    }

    @Override
    public TipoEnergiaComunidade getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( tipoEnergiaComunidadeRepository.existsById(id)) {
            return tipoEnergiaComunidadeRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<TipoEnergiaComunidade> getAllTipoEnergiaComunidade() {
        return tipoEnergiaComunidadeRepository.findAll();
    }

    @Override
    public TipoEnergiaComunidade updateTipoEnergiaComunidade(Long id, TipoEnergiaComunidade tipoEnergiaComunidade) {
        TipoEnergiaComunidade existingTipoEnergiaComunidade = tipoEnergiaComunidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoEnergiaComunidade não encontrado"));

        existingTipoEnergiaComunidade.setDescricaoEnergia(tipoEnergiaComunidade.getDescricaoEnergia());

        return tipoEnergiaComunidadeRepository.save(existingTipoEnergiaComunidade);
    }

    @Override
    public void deleteTipoEnergiaComunidade(Long id) {
        tipoEnergiaComunidadeRepository.deleteById(id);
    }
}
