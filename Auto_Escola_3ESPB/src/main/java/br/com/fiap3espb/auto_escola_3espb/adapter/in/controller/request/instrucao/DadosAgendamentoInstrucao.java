package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoInstrucao(
        @NotNull
        Long idAluno,
        Long idInstrutor,
        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data) {
}