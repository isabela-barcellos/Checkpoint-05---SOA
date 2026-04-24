package br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {

    @Query(value = """
            select count(*) from instrucoes
            where aluno_id = :alunoId
            and status = 'AGENDADA'
            and date(data) = date(:referencia)
            """, nativeQuery = true)
    long countAgendadasDoAlunoNoDia(@Param("alunoId") Long alunoId, @Param("referencia") LocalDateTime referencia);

    @Query(value = """
            select count(*) from instrucoes
            where status = 'AGENDADA'
            and instrutor_id = :instrutorId
            and data = :data
            """, nativeQuery = true)
    long countInstrutorComInstrucaoNaMesmaDataHora(
            @Param("instrutorId") Long instrutorId,
            @Param("data") LocalDateTime data);
}
