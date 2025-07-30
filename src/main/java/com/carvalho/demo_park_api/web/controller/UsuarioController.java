package com.carvalho.demo_park_api.web.controller;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.service.UsuarioService;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import com.carvalho.demo_park_api.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org .springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
    Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDTO));
    return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> getById(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);
    return ResponseEntity.ok(user);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario user = usuarioService.editarSenha(id, usuario.getPassword());
    return ResponseEntity.ok(user);
  }

  @GetMapping
  public ResponseEntity<List<Usuario>> getAll() {
    List<Usuario> users = usuarioService.buscarTodos();
    return ResponseEntity.ok(users);
  }
}
