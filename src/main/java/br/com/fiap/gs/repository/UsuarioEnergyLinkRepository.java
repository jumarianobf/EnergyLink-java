package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.UsuarioEnergyLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioEnergyLinkRepository extends JpaRepository<UsuarioEnergyLink, Long> {
    Optional<UsuarioEnergyLink> findById(Long id);
}
