package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrucaoRepository;
import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConflitoHorarioInstrutor implements ValidadorAgendamento {

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        if(dados.idInstrutor() == null) {
            return;
        }
        long conflitos = instrucaoRepository.countInstrutorComInstrucaoNaMesmaDataHora(
                dados.idInstrutor(),
                dados.data()
        );
        if(conflitos > 0) {
            throw new ValidacaoException("O instrutor já possui outra instrução agendada na mesma data e hora!");
        }
    }
}
