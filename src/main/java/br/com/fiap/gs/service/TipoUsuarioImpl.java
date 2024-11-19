package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.TipoUsuario;
import br.com.fiap.gs.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioImpl implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuario createTipoUsuario(TipoUsuario request) {
        return tipoUsuarioRepository.save(request);
    }

    @Override
    public TipoUsuario getById(Long id) throws ChangeSetPersister.NotFoundException {
        return tipoUsuarioRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<TipoUsuario> getAllTipoUsuario() {
        return tipoUsuarioRepository.findAll();
    }

    @Override
    public TipoUsuario updateTipoUsuario(Long id, TipoUsuario tipoUsuario) {
        TipoUsuario existingTipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoUsuario não encontrado"));

        existingTipoUsuario.setDescricaoTipo(tipoUsuario.getDescricaoTipo());

        return tipoUsuarioRepository.save(existingTipoUsuario);
    }

    @Override
    public void deleteTipoUsuario(Long id) {
        tipoUsuarioRepository.deleteById(id);
    }
}
