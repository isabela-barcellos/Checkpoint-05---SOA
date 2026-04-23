package br.com.fiap3espb.auto_escola_3espb.application.core.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

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
}