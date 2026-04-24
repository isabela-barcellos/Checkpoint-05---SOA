package br.com.fiap3espb.auto_escola_3espb.application.core.domain.model;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.Especialidade;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.vo.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "instrutores")
@Entity(name = "Instrutor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cnh;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(
            String nome,
            String telefone,
            Endereco endereco) {
        if(nome != null) {
            this.nome = nome;
        }
        if(telefone != null) {
            this.telefone = telefone;
        }
        if(endereco != null) {
            this.endereco.atualizarInformacoes(endereco);
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
