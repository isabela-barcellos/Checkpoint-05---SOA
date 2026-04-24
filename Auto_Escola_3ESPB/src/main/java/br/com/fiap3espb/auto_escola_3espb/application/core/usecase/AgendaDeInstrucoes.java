package br.com.fiap3espb.auto_escola_3espb.application.core.usecase;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Aluno;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrucao;
import br.com.fiap3espb.auto_escola_3espb.exception.type.aluno.AlunoNotFoundException;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.AlunoRepository;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrucaoRepository;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import br.com.fiap3espb.auto_escola_3espb.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrutor;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrutor.InstrutorNotFoundException;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeInstrucoes {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Autowired
    private List<ValidadorAgendamento> validadoresAgendamento;

    @Transactional
    public DadosDetalhamentoInstrucao agendar(DadosAgendamentoInstrucao dados) {
        //Validações
        if(!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if(dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }

        //Regras do negócio
        validadoresAgendamento.forEach(v -> v.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());

        Instrutor instrutor = escolherInstrutor(dados);
        if(instrutor == null) {
            throw new ValidacaoException("Não existe instrutor disponível para a data / hora informada!");
        }

        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.data());
        instrucaoRepository.save(instrucao);
        return new DadosDetalhamentoInstrucao(instrucao);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dados) {
        if(dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quanto o instrutor não for informado!");
        }
        return instrutorRepository
                .escolherInstrutorAleatorioDisponivel(dados.especialidade().name(), dados.data())
                .orElse(null);
    }
    public List<DadosDetalhamentoInstrucao> listar() {
        // Alterado de 'repository' para 'instrucaoRepository' para bater com seu @Autowired
        return instrucaoRepository.findAll().stream()
                .map(DadosDetalhamentoInstrucao::new)
                .toList();
    }
}