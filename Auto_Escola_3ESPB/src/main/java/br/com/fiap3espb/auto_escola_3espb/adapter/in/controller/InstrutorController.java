package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.fiap3espb.auto_escola_3espb.application.core.usecase.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(
            @RequestBody @Valid DadosCadastroInstrutor dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoInstrutor dto = service.cadastrarInstrutor(dados);
        URI uri = uriBuilder.path("/instrutores/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemInstrutor>> listarInstrutores(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listarInstrutores(paginacao));
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizarInstrutor(@RequestBody @Valid DadosAtualizacaoInstrutor dados) {
        DadosDetalhamentoInstrutor dto = service.atualizarInstrutor(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id) {
        service.excluirInstrutor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(@PathVariable Long id) {
        DadosDetalhamentoInstrutor dto = service.detalharInstrutor(id);
        return ResponseEntity.ok(dto);
    }
}