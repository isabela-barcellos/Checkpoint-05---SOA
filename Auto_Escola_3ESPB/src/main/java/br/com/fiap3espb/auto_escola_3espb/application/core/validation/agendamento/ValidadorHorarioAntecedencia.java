package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento {
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime agendamento = dados.data();
        LocalDateTime agora = LocalDateTime.now();

        Long antecedencia = Duration.between(agora, agora).toMinutes();

        if(antecedencia < 30) {
            throw new ValidacaoException("O agendamento deve ser feito com antecêdencia mínima de 30 minutos!");
        }
    }
}