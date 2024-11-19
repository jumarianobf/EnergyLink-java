package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.PerfilUsuarioAppDTO;
import br.com.fiap.gs.entity.*;
import br.com.fiap.gs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioAppImpl implements PerfilUsuarioAppService {

    @Autowired
    private PerfilUsuarioAppRepository perfilUsuarioAppRepository;

    @Autowired
    private ComunidadeProdutoraRepostitory comunidadeProdutoraRepostitory;

    @Autowired
    private UsuarioEnergyLinkRepository usuarioEnergyLinkRepository;

    @Autowired
    private FabricaParceriaRepository fabricaParceriaRepository;


    @Override
    public PerfilUsuarioApp createPerfilUsuarioApp(PerfilUsuarioAppDTO request) {
        UsuarioEnergyLink usuarioEnergyLink = usuarioEnergyLinkRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("UsuarioEnergyLink Nao Encontrado: "));

        ComunidadeProdutora comunidadeProdutora1 = comunidadeProdutoraRepostitory.findById(request.getIdComunidade())
                .orElseThrow(() -> new RuntimeException("ComunidadeProdutora Nao Encontrado: "));

        FabricaParceira fabricaParceira = fabricaParceriaRepository.findById(request.getIdFabrica())
                .orElseThrow(() -> new RuntimeException("Fabrica Nao Encontrado: "));

        return perfilUsuarioAppRepository.save(getPerfilUsuarioApp(request, usuarioEnergyLink, comunidadeProdutora1, fabricaParceira));
    }

    private PerfilUsuarioApp getPerfilUsuarioApp(PerfilUsuarioAppDTO request, UsuarioEnergyLink usuarioEnergyLink, ComunidadeProdutora comunidadeProdutora1, FabricaParceira fabricaParceira) {
        return PerfilUsuarioApp.builder()
                .idUsuario(usuarioEnergyLink)
                .idComunidade(comunidadeProdutora1)
                .idFabrica(fabricaParceira)
                .descricaoPerfil(request.getDescricaoPerfil())
                .build();
    }

    @Override
    public PerfilUsuarioApp getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (perfilUsuarioAppRepository.existsById(id)) {
            return perfilUsuarioAppRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<PerfilUsuarioApp> getAllPerfilUsuarioApp() {
        return perfilUsuarioAppRepository.findAll();
    }

    @Override
    public PerfilUsuarioApp updatePerfilUsuarioApp(Long id, PerfilUsuarioAppDTO perfilUsuarioAppDTO) {
        PerfilUsuarioApp existingPerfilUsuarioApp = perfilUsuarioAppRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil do usuario não encontrado"));

        UsuarioEnergyLink usuarioEnergyLink = usuarioEnergyLinkRepository.findById(perfilUsuarioAppDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("UsuarioEnergyLink Nao Encontrado: "));

        ComunidadeProdutora comunidadeProdutora1 = comunidadeProdutoraRepostitory.findById(perfilUsuarioAppDTO.getIdComunidade())
                .orElseThrow(() -> new RuntimeException("ComunidadeProdutora Nao Encontrado: "));

        FabricaParceira fabricaParceira = fabricaParceriaRepository.findById(perfilUsuarioAppDTO.getIdFabrica())
                .orElseThrow(() -> new RuntimeException("Fabrica Nao Encontrado: "));

        existingPerfilUsuarioApp.setIdUsuario(usuarioEnergyLink);
        existingPerfilUsuarioApp.setIdComunidade(comunidadeProdutora1);
        existingPerfilUsuarioApp.setIdFabrica(fabricaParceira);
        existingPerfilUsuarioApp.setDescricaoPerfil(perfilUsuarioAppDTO.getDescricaoPerfil());

        return perfilUsuarioAppRepository.save(existingPerfilUsuarioApp);
    }

    @Override
    public void deletePerfilUsuarioApp(Long id) {
        perfilUsuarioAppRepository.deleteById(id);
    }
}
