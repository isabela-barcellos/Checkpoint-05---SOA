package br.com.fiap3espb.auto_escola_3espb.application.core.usecase;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.mapper.EnderecoMapper;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.mapper.InstrutorMapper;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrutor;
import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence.InstrutorRepository;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrutor.InstrutorNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorService {
    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;
    private final EnderecoMapper enderecoMapper;

    public InstrutorService(
            InstrutorRepository repository,
            InstrutorMapper mapper,
            EnderecoMapper enderecoMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.enderecoMapper = enderecoMapper;
    }

    @Transactional
    public DadosDetalhamentoInstrutor cadastrarInstrutor(DadosCadastroInstrutor dados) {
        Instrutor instrutor = mapper.toDomain(dados);
        Instrutor saved = repository.save(instrutor);
        return mapper.toDetailsDTO(saved);
    }

    public Page<DadosListagemInstrutor> listarInstrutores(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toListDTO);
    }

    @Transactional
    public DadosDetalhamentoInstrutor atualizarInstrutor(DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.findById(dados.id()).orElseThrow(()
                -> new InstrutorNotFoundException("ID do instrutor informado não existe!"));
        instrutor.atualizarInformacoes(
                dados.nome(),
                dados.telefone(),
                enderecoMapper.toDomain(dados.endereco())
        );
        Instrutor saved = repository.save(instrutor);
        return mapper.toDetailsDTO(saved);
    }

    @Transactional
    public void excluirInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id).orElseThrow(()
                -> new InstrutorNotFoundException("ID do instrutor informado não existe!"));
        instrutor.excluir();
        repository.save(instrutor);
    }

    public DadosDetalhamentoInstrutor detalharInstrutor(Long id) {
        Instrutor instrutor = repository.findById(id).orElseThrow(()
                -> new InstrutorNotFoundException("ID do instrutor informado não existe!"));
        return mapper.toDetailsDTO(instrutor);
    }
}