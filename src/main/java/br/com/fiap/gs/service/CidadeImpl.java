package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.CidadeDTO;
import br.com.fiap.gs.entity.*;
import br.com.fiap.gs.repository.CidadeRepository;
import br.com.fiap.gs.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CidadeImpl implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Cidade createCidade(CidadeDTO request) {
        Estado estado = estadoRepository.findById(request.getIdEstado()).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return cidadeRepository.save(getCidade(request, estado));

    }

    private Cidade getCidade(CidadeDTO request, Estado estado) {
        return Cidade.builder()
                .idEstado(estado)
                .nomeCidade(String.valueOf(request.getNomeCidade()))
                .build();
    }

    @Override
    public Cidade getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (estadoRepository.existsById(id)) {
            return cidadeRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Cidade> getAllCidade() {
        return cidadeRepository.findAll();
    }

    @Override
    public Cidade updateCidade(Long id, CidadeDTO cidadeDTO) {
        Cidade existingCidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cidade não encontrado"));

        Estado estado = estadoRepository.findById(cidadeDTO.getIdEstado())
                .orElseThrow(() -> new NoSuchElementException("Estado não encontrado com ID: " + cidadeDTO.getIdEstado()));

        existingCidade.setIdEstado(estado);
        existingCidade.setNomeCidade(cidadeDTO.getNomeCidade());

        return cidadeRepository.save(existingCidade);

    }


    @Override
    public void deleteCidade(Long id) {
        cidadeRepository.deleteById(id);
    }
}
