package com.carvalho.demo_park_api.web.controller;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.service.UsuarioService;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioSenhaDTO;
import com.carvalho.demo_park_api.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);
    return ResponseEntity.ok(UsuarioMapper.toDto(user));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) {
    Usuario user = usuarioService.editarSenha(id, usuarioSenhaDTO.getSenhaAtual(), usuarioSenhaDTO.getNovaSenha(), usuarioSenhaDTO.getConfirmaSenha());
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
    List<Usuario> users = usuarioService.buscarTodos();
    return ResponseEntity.ok(UsuarioMapper.toListDto(users));
  }
}
