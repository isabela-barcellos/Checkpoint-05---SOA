package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller;

import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.AgendaDeInstrucoes;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    public ResponseEntity agendarInstrucao(@RequestBody @Valid DadosAgendamentoInstrucao dados) {
        DadosDetalhamentoInstrucao dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }
}