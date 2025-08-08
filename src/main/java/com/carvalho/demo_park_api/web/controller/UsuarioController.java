package com.carvalho.demo_park_api.web.controller;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.service.UsuarioService;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioSenhaDTO;
import com.carvalho.demo_park_api.web.dto.mapper.UsuarioMapper;
import com.carvalho.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(
    name = "Usuários",
    description = "Contém todos as operações relativas aos recursos para cadastro, edição e leitura de usuários."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @Operation(
      summary="Criar um novo usuário.",
      description = "Recurso para criar um novo usuário.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Recurso criado com sucesso.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                  implementation = UsuarioResponseDTO.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "409",
              description = "Usuário e-mail já cadastrado no sistema.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = ErrorMessage.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "422",
              description = "Recurso não processado por dados de entrada inválidos.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = ErrorMessage.class
                  )
              )
          ),
      }
  )
  @PostMapping
  public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
    Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDTO));
    return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);
    return ResponseEntity.ok(UsuarioMapper.toDto(user));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) {
    Usuario user = usuarioService.editarSenha(id, usuarioSenhaDTO.getSenhaAtual(), usuarioSenhaDTO.getNovaSenha(), usuarioSenhaDTO.getConfirmaSenha());
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
    List<Usuario> users = usuarioService.buscarTodos();
    return ResponseEntity.ok(UsuarioMapper.toListDto(users));
  }
}
