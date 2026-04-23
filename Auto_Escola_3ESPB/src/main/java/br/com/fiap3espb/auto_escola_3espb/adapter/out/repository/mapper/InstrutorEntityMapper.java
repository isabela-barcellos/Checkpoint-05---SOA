package br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.mapper;

import br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.entity.InstrutorEntity;
import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Instrutor;
import org.springframework.stereotype.Component;

@Component
public class InstrutorEntityMapper {
    public Instrutor toDomain(InstrutorEntity entity) {
        return new Instrutor(
                entity.getId(),
                entity.getAtivo(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCnh(),
                entity.getEspecialidade(),
                entity.getEndereco()
        );
    }

    public InstrutorEntity toEntity(Instrutor instrutor) {
        return new InstrutorEntity(
                instrutor.getId(),
                instrutor.getAtivo(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco()
        );
    }
}