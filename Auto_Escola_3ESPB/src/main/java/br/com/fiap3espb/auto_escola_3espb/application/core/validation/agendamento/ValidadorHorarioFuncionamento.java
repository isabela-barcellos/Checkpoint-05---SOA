package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime agendamento = dados.data();

        Boolean domingo = agendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean preAbertura = agendamento.getHour() < 6;
        Boolean posFechamento = agendamento.getHour() > (21 - 1);

        if(domingo || preAbertura || posFechamento) {
            throw new ValidacaoException("Tentativa de agendamento fora do horário de funcionamento da Auto Escola!");
        }
    }
}