package br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
}