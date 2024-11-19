package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.EntidadeBase;
import br.com.fiap.gs.repository.EntidadeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadeBaseImpl implements EntidadeBaseService {

    @Autowired
    private EntidadeBaseRepository entidadeBaseRepository;

    @Override
    public EntidadeBase createEntidadeBase(EntidadeBase entidadeBase) {
        return entidadeBaseRepository.save(entidadeBase);
    }

    @Override
    public EntidadeBase getById(Long id) throws ChangeSetPersister.NotFoundException {
        return entidadeBaseRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<EntidadeBase> getAllEntidades() {
        return entidadeBaseRepository.findAll();
    }

    @Override
    public EntidadeBase updateEntidadeBase(Long id, EntidadeBase entidadeBase) {
        EntidadeBase existingEntidade = entidadeBaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EntidadeBase não encontrada"));

        existingEntidade.setNomeEntidade(entidadeBase.getNomeEntidade());

        return entidadeBaseRepository.save(existingEntidade);
    }

    @Override
    public void deleteEntidadeBase(Long id) {
        entidadeBaseRepository.deleteById(id);
    }
}
