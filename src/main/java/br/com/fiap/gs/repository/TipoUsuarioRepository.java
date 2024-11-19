package br.com.fiap.gs.repository;

import br.com.fiap.gs.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
