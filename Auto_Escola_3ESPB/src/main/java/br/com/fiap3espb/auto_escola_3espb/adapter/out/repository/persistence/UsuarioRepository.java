package br.com.fiap3espb.auto_escola_3espb.adapter.out.repository.persistence;

import br.com.fiap3espb.auto_escola_3espb.application.core.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     UserDetails findByLogin(String login);
}