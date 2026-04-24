package br.com.fiap3espb.auto_escola_3espb.application.core.domain.vo;

import br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.endereco.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Endereco(DadosEndereco dados) {
        this(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf(),
                dados.cep()
        );
    }

    public void atualizarInformacoes(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cep) {
        if(logradouro != null) {
            this.logradouro = logradouro;
        }
        if(numero != null) {
            this.numero = numero;
        }
        if(complemento != null) {
            this.complemento = complemento;
        }
        if(bairro != null) {
            this.bairro = bairro;
        }
        if(cidade != null) {
            this.cidade = cidade;
        }
        if(uf != null) {
            this.uf = uf;
        }
        if(cep != null) {
            this.cep = cep;
        }
    }

    public void atualizarInformacoes(Endereco novo) {
        if(novo == null) {
            return;
        }
        atualizarInformacoes(
                novo.getLogradouro(),
                novo.getNumero(),
                novo.getComplemento(),
                novo.getBairro(),
                novo.getCidade(),
                novo.getUf(),
                novo.getCep()
        );
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados == null) {
            return;
        }
        atualizarInformacoes(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf(),
                dados.cep()
        );
    }
}
