package br.com.fiap.api.usuarios_pettech.service;

import br.com.fiap.api.usuarios_pettech.controller.exception.ControllerNotFoundException;
import br.com.fiap.api.usuarios_pettech.dto.UsuarioDTO;
import br.com.fiap.api.usuarios_pettech.entity.Usuario;
import br.com.fiap.api.usuarios_pettech.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Page<UsuarioDTO> findAll(Pageable pageable) {

        Page<Usuario> usuarios = repo.findAll(pageable);

        return usuarios.map(this::toUsuarioDTO);
    }

    public UsuarioDTO findById(Long id) {

        return toUsuarioDTO(repo.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado")));
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        return toUsuarioDTO(repo.save(toUsuario(usuarioDTO)));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO){
        try {
            Usuario usuario = repo.getReferenceById(id);

            usuario.setNome(usuarioDTO.nome());
            usuario.setCpf(usuarioDTO.cpf());
            usuario.setDataNascimento(usuarioDTO.dataNascimento());
            usuario.setEmail(usuarioDTO.email());

            return toUsuarioDTO(repo.save(usuario));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId()
                , usuario.getNome()
                , usuario.getEmail()
                , usuario.getCpf()
                , usuario.getDataNascimento());
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.id()
                , usuarioDTO.nome()
                , usuarioDTO.email()
                , usuarioDTO.cpf()
                , usuarioDTO.dataNascimento());
    }
}
