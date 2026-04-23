package br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;

public interface ValidadorAgendamento {
    void validar(DadosAgendamentoInstrucao dados);
}