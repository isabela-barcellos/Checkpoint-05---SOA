package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrucaoRepository;
import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorLimiteInstrucoesAlunoPorDia implements ValidadorAgendamento {

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        long total = instrucaoRepository.countAgendadasDoAlunoNoDia(dados.idAluno(), dados.data());
        if(total >= 2) {
            throw new ValidacaoException("Não é permitido agendar mais de duas instruções no mesmo dia para o mesmo aluno!");
        }
    }
}
