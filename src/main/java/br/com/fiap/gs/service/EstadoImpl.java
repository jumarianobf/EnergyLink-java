package br.com.fiap.gs.service;

import org.springframework.stereotype.Service;
import br.com.fiap.gs.controller.dto.EstadoDTO;
import br.com.fiap.gs.entity.Estado;
import br.com.fiap.gs.entity.Pais;
import br.com.fiap.gs.repository.EstadoRepository;
import br.com.fiap.gs.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EstadoImpl implements EstadoService{

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;


    @Override
    public Estado createEstado(EstadoDTO request) {
        Pais pais = paisRepository.findById(request.getIdPais()).orElseThrow(() -> new RuntimeException("Pais não encontrado"));
        return estadoRepository.save(getEstado(request, pais));

    }
    private Estado getEstado(EstadoDTO request, Pais pais) {
        return Estado.builder()
                .nomeEstado(request.getNomeEstado())
                .idPais(pais)
                .build();
    }

    @Override
    public Estado getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (estadoRepository.existsById(id)) {
            return estadoRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Estado> getAllEstado() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado updateEstado(Long id, EstadoDTO estadoDTO) {
        Estado existingEstado = estadoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estado não encontrado"));

        Pais pais = paisRepository.findById(estadoDTO.getIdPais())
                .orElseThrow(() -> new NoSuchElementException("Pais não encontrado"));

        existingEstado.setNomeEstado(estadoDTO.getNomeEstado());
        existingEstado.setIdPais(pais);

        return estadoRepository.save(existingEstado);
    }

    @Override
    public void deleteEstado(Long id) {
        estadoRepository.deleteById(id);
    }
}
