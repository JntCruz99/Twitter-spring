package com.jntcruz.twitter.repository;

import com.jntcruz.twitter.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
