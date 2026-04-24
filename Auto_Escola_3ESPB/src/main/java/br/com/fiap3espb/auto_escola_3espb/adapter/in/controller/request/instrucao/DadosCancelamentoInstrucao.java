package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.MotivoCancelamentoInstrucao;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoInstrucao(
        @NotNull
        MotivoCancelamentoInstrucao motivo) {
}
