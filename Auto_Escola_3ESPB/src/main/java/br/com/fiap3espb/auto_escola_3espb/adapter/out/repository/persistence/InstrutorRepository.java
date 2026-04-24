package br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = """
            select * from instrutores i
            where i.ativo = true
            and i.especialidade = :especialidade
            and i.id not in (
                select instrutor_id from instrucoes
                where status = 'AGENDADA'
                and data = :data
            )
            order by rand()
            limit 1
            """, nativeQuery = true)
    Optional<Instrutor> escolherInstrutorAleatorioDisponivel(
            @Param("especialidade") String especialidade,
            @Param("data") LocalDateTime data);
}
