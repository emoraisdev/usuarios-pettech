package br.com.fiap.api.usuarios_pettech.service.validation;

import br.com.fiap.api.usuarios_pettech.dto.UsuarioDTO;
import br.com.fiap.api.usuarios_pettech.entity.Usuario;
import br.com.fiap.api.usuarios_pettech.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CriacaoUsuarioValidator implements ConstraintValidator<CriacaoUsuarioValid, UsuarioDTO> {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public void initialize(CriacaoUsuarioValid criacaoUsuarioValid){

    }

    @Override
    public boolean isValid(UsuarioDTO dto, ConstraintValidatorContext context) {

        Optional<Usuario> usuario= repo.findByEmail(dto.email());

        return usuario.isEmpty();
    }
}
