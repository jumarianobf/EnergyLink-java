package br.com.fiap.gs.controller;


import br.com.fiap.gs.controller.dto.PerfilUsuarioAppDTO;
import br.com.fiap.gs.entity.PerfilUsuarioApp;
import br.com.fiap.gs.model.PerfilUsuarioAppModel;
import br.com.fiap.gs.service.PerfilUsuarioAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/perfil-usuario-app")
public class PerfilUsuarioAppController {

    @Autowired
    private PerfilUsuarioAppService perfilUsuarioAppService;

    @Operation(summary = "Cadastrar um novo perfil de usuario", description = "Endpoint para cadastrar um novo perfil de usuario no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilUsuarioAppModel> createPerfilUsuarioApp(@Valid @RequestBody PerfilUsuarioAppDTO perfilUsuarioAppDTO) {
        try {
            PerfilUsuarioApp savedPerfilUsuarioApp = perfilUsuarioAppService.createPerfilUsuarioApp(perfilUsuarioAppDTO);
            PerfilUsuarioAppModel responseModel = toResponseModel(savedPerfilUsuarioApp);
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getPerfilUsuarioAppById(savedPerfilUsuarioApp.getIdPerfil())).withSelfRel());
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getAllPerfilUsuarioApp()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private PerfilUsuarioAppModel toResponseModel(PerfilUsuarioApp savedPerfilUsuarioApp) {
        return new PerfilUsuarioAppModel(
                savedPerfilUsuarioApp.getIdUsuario().getIdUsuario(),
                savedPerfilUsuarioApp.getIdComunidade().getIdComunidade(),
                savedPerfilUsuarioApp.getIdFabrica().getIdFabrica(),
                savedPerfilUsuarioApp.getDescricaoPerfil()
        );
    }
    @Operation(summary = "Buscar perfil usuário por ID", description = "Recupera as informações de um perfil de usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil de usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Perfil de usuário não encontrado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<PerfilUsuarioAppModel> getPerfilUsuarioAppById(@PathVariable("id") Long id) {
        try {
            PerfilUsuarioApp perfilUsuarioApp = perfilUsuarioAppService.getById(id);
            PerfilUsuarioAppModel responseModel = toResponseModel(perfilUsuarioApp);
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getPerfilUsuarioAppById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getAllPerfilUsuarioApp()).withRel("listar"));
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).updatePerfilUsuarioApp(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).deletePerfilUsuarioApp(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos os perfis de usuários", description = "Retorna a lista de todos os perfis de usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de perfis de usuários retornada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity<List<PerfilUsuarioAppModel>> getAllPerfilUsuarioApp() {
        List<PerfilUsuarioApp> perfilUsuarioAppList = perfilUsuarioAppService.getAllPerfilUsuarioApp();
        List<PerfilUsuarioAppModel> responseModel = perfilUsuarioAppList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModel.forEach(perfilUsuarioAppModel -> {
            perfilUsuarioAppModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getAllPerfilUsuarioApp()).withRel("listar"));
        });
        return ResponseEntity.ok(responseModel);
    }

    @Operation(summary = "Atualizar um perfil usuário", description = "Atualiza as informações de um perfil de usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Perfil de usuário não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<PerfilUsuarioAppModel> updatePerfilUsuarioApp(
            @PathVariable("id") Long id,
            @RequestBody @Valid PerfilUsuarioAppDTO perfilUsuarioAppDTO) {
        try {
            PerfilUsuarioApp updatedPerfilUsuarioApp = perfilUsuarioAppService.updatePerfilUsuarioApp(id, perfilUsuarioAppDTO);
            PerfilUsuarioAppModel responseModel = toResponseModel(updatedPerfilUsuarioApp);
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getPerfilUsuarioAppById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(PerfilUsuarioAppController.class).getAllPerfilUsuarioApp()).withRel("listar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um perfil de usuário", description = "Exclui um perfil de usuário existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Perfil de usuário não encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePerfilUsuarioApp(@PathVariable("id") Long id) {
        try {
            perfilUsuarioAppService.deletePerfilUsuarioApp(id);
            return new ResponseEntity<>("Perfil excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
