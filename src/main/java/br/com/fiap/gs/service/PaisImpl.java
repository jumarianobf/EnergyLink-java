package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.Pais;
import br.com.fiap.gs.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisImpl implements PaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public Pais createPais(Pais request) {
        return paisRepository.save(request);
    }

    @Override
    public Pais getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( paisRepository.existsById(id)) {
            return paisRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Pais> getAllPais() {
        return paisRepository.findAll();
    }

    @Override
    public Pais updatePais(Long id, Pais pais) {
        Pais existingPais = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        existingPais.setNomePais(pais.getNomePais());

        return paisRepository.save(existingPais);
    }

    @Override
    public void deletePais(Long id) {
        paisRepository.deleteById(id);
    }
}
