package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.endereco.DadosEndereco;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.Especialidade;

public record DadosDetalhamentoInstrutor(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        DadosEndereco endereco) {
}