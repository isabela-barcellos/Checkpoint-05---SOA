package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrutor;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoInstrutor(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
