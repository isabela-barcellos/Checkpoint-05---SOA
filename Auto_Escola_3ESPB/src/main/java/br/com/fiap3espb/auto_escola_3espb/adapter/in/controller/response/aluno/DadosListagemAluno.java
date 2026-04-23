package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.aluno;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Aluno;

public record DadosListagemAluno(
        Long id,
        String nome,
        String email,
        String cpf) {
    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf());
    }
}