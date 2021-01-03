package com.example.postgresdemotwitter.repository;

import com.example.postgresdemotwitter.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.ComponentScan; 


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}