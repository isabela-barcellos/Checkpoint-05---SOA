package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.AgendaDeInstrucoes;
import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.CancelamentoDeInstrucao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @Autowired
    private CancelamentoDeInstrucao cancelamentoDeInstrucao;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoInstrucao> agendarInstrucao(@RequestBody @Valid DadosAgendamentoInstrucao dados) {
        DadosDetalhamentoInstrucao dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/cancelamento")
    public ResponseEntity<DadosDetalhamentoInstrucao> cancelarInstrucao(
            @PathVariable Long id,
            @RequestBody @Valid DadosCancelamentoInstrucao dados) {
        return ResponseEntity.ok(cancelamentoDeInstrucao.cancelar(id, dados));
    }
}