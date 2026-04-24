package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.AgendaDeInstrucoes;
import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.CancelamentoDeInstrucao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/cancelamento")
    public ResponseEntity<DadosDetalhamentoInstrucao> cancelarInstrucao(
            @RequestBody @Valid DadosCancelamentoInstrucao dados) {

        return ResponseEntity.ok(cancelamentoDeInstrucao.cancelar(dados.idInstrucao(), dados));
    }
    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoInstrucao>> listarInstrucao() {

        return ResponseEntity.ok(agenda.listar());
    }
}