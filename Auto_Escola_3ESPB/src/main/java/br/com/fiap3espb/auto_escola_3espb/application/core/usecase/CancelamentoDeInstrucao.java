package br.com.fiap3espb.auto_escola_3espb.application.core.usecase;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrucaoRepository;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrucao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelamentoDeInstrucao {

    private final InstrucaoRepository instrucaoRepository;

    public CancelamentoDeInstrucao(InstrucaoRepository instrucaoRepository) {
        this.instrucaoRepository = instrucaoRepository;
    }

    @Transactional
    public DadosDetalhamentoInstrucao cancelar(Long idInstrucao, DadosCancelamentoInstrucao dados) {
        Instrucao instrucao = instrucaoRepository.findById(idInstrucao)
                .orElseThrow(() -> new EntityNotFoundException("Instrução não encontrada."));
        instrucao.cancelar(dados.motivoCancelamento(), LocalDateTime.now());
        return new DadosDetalhamentoInstrucao(instrucaoRepository.save(instrucao));
    }
}
