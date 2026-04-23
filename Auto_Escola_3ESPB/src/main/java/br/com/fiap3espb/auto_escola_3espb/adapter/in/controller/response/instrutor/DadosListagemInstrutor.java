package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.Especialidade;

public record DadosListagemInstrutor(
        Long id,
        String nome,
        String email,
        Especialidade especialidade) {
}