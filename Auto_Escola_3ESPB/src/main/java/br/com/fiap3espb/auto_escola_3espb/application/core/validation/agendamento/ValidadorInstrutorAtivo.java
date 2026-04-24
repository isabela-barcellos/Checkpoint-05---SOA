package br.com.fiap3espb.auto_escola_3espb.application.core.validation.agendamento;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrutorRepository;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrutor;
import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrutor.InstrutorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        if(dados.idInstrutor() == null) {
            return;
        }
        Instrutor instrutor = instrutorRepository.findById(dados.idInstrutor())
                .orElseThrow(() -> new InstrutorNotFoundException("ID do instrutor informado não existe!"));
        if(!Boolean.TRUE.equals(instrutor.getAtivo())) {
            throw new ValidacaoException("Instrução não pode ser agendada com instrutor inativo!");
        }
    }
}
