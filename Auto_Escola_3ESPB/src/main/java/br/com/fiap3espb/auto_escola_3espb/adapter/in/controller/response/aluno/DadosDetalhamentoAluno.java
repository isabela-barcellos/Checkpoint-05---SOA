package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.aluno;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Aluno;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.vo.Endereco;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getTelefone(), aluno.getCpf(), aluno.getEndereco());
    }
}
