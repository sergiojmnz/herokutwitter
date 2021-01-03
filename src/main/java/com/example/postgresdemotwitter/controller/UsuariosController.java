package com.example.postgresdemotwitter.controller;

import com.example.postgresdemotwitter.exception.ResourceNotFoundException;
import com.example.postgresdemotwitter.model.Usuario;
import com.example.postgresdemotwitter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.ComponentScan; 



//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin()
@RestController
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public Page<Usuario> getUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }


    @PostMapping("/usuarios")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/usuarios/{usuarioId}")
    public Usuario updateusuario(@PathVariable Long usuarioId,
                                   @RequestBody Usuario usuarioRequest) {
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    usuario.setTitle(usuarioRequest.getTitle());
                    usuario.setDescription(usuarioRequest.getDescription());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + usuarioId));
    }


    @DeleteMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + usuarioId));
    }
}