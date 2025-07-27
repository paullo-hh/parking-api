package com.carvalho.demo_park_api.web.controller;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org .springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
    Usuario user = usuarioService.salvar(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
}
