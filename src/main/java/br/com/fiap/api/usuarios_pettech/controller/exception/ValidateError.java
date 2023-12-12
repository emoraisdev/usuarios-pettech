package br.com.fiap.api.usuarios_pettech.controller.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidateError extends StandardError {

    private List<ValidateMessage> mensagens = new ArrayList<>();

    public void addMensagem(String campo, String mensagem){

        mensagens.add(new ValidateMessage(campo, mensagem));
    }

}
