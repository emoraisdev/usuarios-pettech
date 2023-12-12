package br.com.fiap.api.usuarios_pettech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTO(Long id,

                         @NotBlank(message = "O nome não pode estar vazio.")
                         String nome,
                         @Email(message = "E-mail inválido.")
                         String email,
                         @CPF
                         String cpf,
                         LocalDate dataNascimento) {
}
