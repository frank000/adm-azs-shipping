package io.github.frank000.clientes.rest.exception;

public class UsuarioCadastradoException extends RuntimeException{
    public UsuarioCadastradoException(String login) {
        super("Usuário já cadastrado para o Login: "  + login);
    }
}
