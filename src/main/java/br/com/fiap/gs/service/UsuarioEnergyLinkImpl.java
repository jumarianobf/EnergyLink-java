package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.UsuarioEnergyLinkDTO;
import br.com.fiap.gs.entity.TipoUsuario;
import br.com.fiap.gs.entity.UsuarioEnergyLink;
import br.com.fiap.gs.repository.TipoUsuarioRepository;
import br.com.fiap.gs.repository.UsuarioEnergyLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioEnergyLinkImpl implements UsuarioEnergyLinkService {

    @Autowired
    private UsuarioEnergyLinkRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public UsuarioEnergyLink createUsuario(UsuarioEnergyLinkDTO usuario) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(usuario.getIdTipoUsuario())
                .orElseThrow(() -> new RuntimeException("Tipo de Usuario não encontrado: " + usuario.getIdTipoUsuario()));

        return usuarioRepository.save(getUsuario(usuario, tipoUsuario));
    }

    private UsuarioEnergyLink getUsuario(UsuarioEnergyLinkDTO usuario, TipoUsuario tipoUsuario) {
        return UsuarioEnergyLink.builder()
                .nomeUsuario(usuario.getNomeUsuario())
                .cpfUsuario(usuario.getCpfUsuario())
                .sobrenome(usuario.getSobrenome())
                .emailUsuario(usuario.getEmailUsuario())
                .senhaUsuario(usuario.getSenhaUsuario())
                .telefoneUsuario(usuario.getTelefoneUsuario())
                .idTipoUsuario(tipoUsuario)
                .build();
    }


    @Override
    public UsuarioEnergyLink getById(Long id) throws ChangeSetPersister.NotFoundException  {
        if( usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<UsuarioEnergyLink> getAllUsuario() {
        return usuarioRepository.findAll();
    }


    @Override
    public UsuarioEnergyLink updateUsuario(Long id, UsuarioEnergyLinkDTO usuario) {
        UsuarioEnergyLink existingUsuario = usuarioRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("Usuario com id não encontrado: " + id));

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(usuario.getIdTipoUsuario())
                .orElseThrow(() -> new RuntimeException("Tipo de Usuario não encontrado" + id));

        existingUsuario.setNomeUsuario(usuario.getNomeUsuario());
        existingUsuario.setCpfUsuario(usuario.getCpfUsuario());
        existingUsuario.setSobrenome(usuario.getSobrenome());
        existingUsuario.setEmailUsuario(usuario.getEmailUsuario());
        existingUsuario.setSenhaUsuario(usuario.getSenhaUsuario());
        existingUsuario.setTelefoneUsuario(usuario.getTelefoneUsuario());
        existingUsuario.setIdTipoUsuario(tipoUsuario);

        return usuarioRepository.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
