package br.com.fiap.api.usuarios_pettech.repository;

import br.com.fiap.api.usuarios_pettech.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
