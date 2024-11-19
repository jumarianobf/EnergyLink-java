package br.com.fiap.gs.controller;

import br.com.fiap.gs.controller.dto.MonitoramentoEnergiaDTO;
import br.com.fiap.gs.entity.MonitoramentoEnergia;
import br.com.fiap.gs.model.MonitoramentoEnergiaModel;
import br.com.fiap.gs.service.MonitoramentoEnergiaService;
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
@RequestMapping("api/montioramento-energia")
public class MonitoramentoEnergiaController {

        @Autowired
        private MonitoramentoEnergiaService monitoramentoEnergiaService;

    @Operation(summary = "Cadastrar um novo monitoramento de energia", description = "Endpoint para cadastrar um novo monitoramento de energia no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "monitoramento de energia criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados", content = @Content)
    })
        @PostMapping("/cadastrar")
        public ResponseEntity<MonitoramentoEnergiaModel> createMonitoramentoEnergia(
                @Valid @RequestBody MonitoramentoEnergiaDTO monitoramentoEnergiaDTO) {
            try {
                MonitoramentoEnergia savedMonitoramentoEnergia = monitoramentoEnergiaService.createMonitoramentoEnergia(monitoramentoEnergiaDTO);
                MonitoramentoEnergiaModel responseModel = toResponseModel(savedMonitoramentoEnergia);
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getMonitoramentoEnergiaById(savedMonitoramentoEnergia.getIdMonitoramento())).withSelfRel());
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getAllMonitoramentoEnergia()).withRel("listar"));
                return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        private MonitoramentoEnergiaModel toResponseModel(MonitoramentoEnergia savedMonitoramentoEnergia) {
            return new MonitoramentoEnergiaModel(
                    savedMonitoramentoEnergia.getIdEntidade().getIdEntidade(),
                    savedMonitoramentoEnergia.getDataMonitoramento(),
                    savedMonitoramentoEnergia.getEnergia(),
                    savedMonitoramentoEnergia.getTipoMonitoramento()
            );
        }

    @Operation(summary = "Buscar monitoramento de energia por ID", description = "Recupera as informações de um monitoramento de energia pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "monitoramento de energia encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "monitoramento de energia não encontrado", content = @Content)
    })
        @GetMapping("{id}")
        public ResponseEntity<MonitoramentoEnergiaModel> getMonitoramentoEnergiaById(
                @PathVariable("id") Long id) {
            try {
                MonitoramentoEnergia monitoramentoEnergia = monitoramentoEnergiaService.getById(id);
                MonitoramentoEnergiaModel responseModel = toResponseModel(monitoramentoEnergia);
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getMonitoramentoEnergiaById(id)).withSelfRel());
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getAllMonitoramentoEnergia()).withRel("listar"));
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).updateMonitoramentoEnergia(id, null)).withRel("atualizar"));
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).deleteMonitoramentoEnergia(id)).withRel("deletar"));
                return ResponseEntity.ok(responseModel);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    @Operation(summary = "Listar todos os monitoramentos de energias", description = "Retorna a lista de todos os monitoramentos de energias cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de monitoramento de energia retornada com sucesso")
        @GetMapping("/listar")
        public ResponseEntity<List<MonitoramentoEnergiaModel>> getAllMonitoramentoEnergia() {
            List<MonitoramentoEnergia> monitoramentoEnergiaList = monitoramentoEnergiaService.getAllMonitoramentoEnergia();
            List<MonitoramentoEnergiaModel> responseModels = monitoramentoEnergiaList.stream()
                    .map(this::toResponseModel)
                    .collect(Collectors.toList());
            responseModels.forEach(model -> model.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getAllMonitoramentoEnergia()).withSelfRel()));
            return ResponseEntity.ok(responseModels);
        }

        @Operation(summary = "Atualizar um monitoramento de energia", description = "Atualiza as informações de um monitoramento de energia existente pelo ID.")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "monitoramento de energia atualizado com sucesso"),
                @ApiResponse(responseCode = "404", description = "monitoramento de energia não encontrado", content = @Content)
        })
        @PutMapping("{id}")
        public ResponseEntity<MonitoramentoEnergiaModel> updateMonitoramentoEnergia(
                @PathVariable("id") Long id,
                @RequestBody @Valid MonitoramentoEnergiaDTO monitoramentoEnergiaDTO) {
            try {
                MonitoramentoEnergia updatedMonitoramentoEnergia = monitoramentoEnergiaService.updateMonitoramentoEnergia(id, monitoramentoEnergiaDTO);
                MonitoramentoEnergiaModel responseModel = toResponseModel(updatedMonitoramentoEnergia);
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getMonitoramentoEnergiaById(id)).withSelfRel());
                responseModel.add(linkTo(methodOn(MonitoramentoEnergiaController.class).getAllMonitoramentoEnergia()).withRel("listar"));
                return ResponseEntity.ok(responseModel);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @Operation(summary = "Excluir um monitoramento de energia", description = "Exclui um monitoramento de energia existente pelo ID.")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "monitoramento de energia excluído com sucesso"),
                @ApiResponse(responseCode = "404", description = "monitoramento de energia não encontrado", content = @Content)
        })
        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteMonitoramentoEnergia(@PathVariable("id") Long id) {
            try {
                monitoramentoEnergiaService.deleteMonitoramentoEnergia(id);
                return new ResponseEntity<>("Monitoramento excluído com sucesso", HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

}
