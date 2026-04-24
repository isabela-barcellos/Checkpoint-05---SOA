package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.AlunoRepository;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlunoAtivo implements ValidadorAgendamento {
    @Autowired
    private AlunoRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        Boolean alunoAtivo = repository.findAtivoById(dados.idAluno());

        if(!Boolean.TRUE.equals(alunoAtivo)) {
            throw new ValidacaoException("Instrução não pode ser agendada para aluno inativo!");
        }
    }
}