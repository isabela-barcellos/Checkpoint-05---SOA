package br.com.fiap3espb.auto_escola_3espb.exception.type.aluno;

public class AlunoNotFoundException extends RuntimeException {
    public AlunoNotFoundException(String message) {
        super(message);
    }
}