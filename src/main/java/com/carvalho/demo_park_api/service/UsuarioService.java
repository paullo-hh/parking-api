package com.carvalho.demo_park_api.service;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario salvar(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }
}
