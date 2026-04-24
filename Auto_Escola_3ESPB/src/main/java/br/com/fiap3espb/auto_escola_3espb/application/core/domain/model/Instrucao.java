package br.com.fiap3espb.auto_escola_3espb.application.core.domain.model;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.MotivoCancelamentoInstrucao;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.enums.StatusInstrucao;
import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusInstrucao status;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamentoInstrucao motivoCancelamento;

    public Instrucao(Long id, Aluno aluno, Instrutor instrutor, LocalDateTime data) {
        this.id = id;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.data = data;
        this.status = StatusInstrucao.AGENDADA;
        this.motivoCancelamento = null;
    }

    public void cancelar(MotivoCancelamentoInstrucao motivo, LocalDateTime agora) {
        if(this.status != StatusInstrucao.AGENDADA) {
            throw new ValidacaoException("Apenas instruções agendadas podem ser canceladas.");
        }
        if(Duration.between(agora, this.data).compareTo(Duration.ofHours(24)) < 0) {
            throw new ValidacaoException("Cancelamento exige antecedência mínima de 24 horas.");
        }
        this.status = StatusInstrucao.CANCELADA;
        this.motivoCancelamento = motivo;
    }
}
