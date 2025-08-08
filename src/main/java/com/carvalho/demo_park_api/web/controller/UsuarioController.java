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
    name = "Usuarios",
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

  @Operation(
      summary="Recuperar um usuário pelo id.",
      description = "Recurso para recuperar um usuário pelo id.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Recurso recuperado com sucesso.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = UsuarioResponseDTO.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Recurso não encontrado.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = ErrorMessage.class
                  )
              )
          )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);
    return ResponseEntity.ok(UsuarioMapper.toDto(user));
  }

  @Operation(
      summary="Atualizar senha.",
      description = "Recurso para Atualizar senha.",
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Senha atualizada com sucesso.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = Void.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Senha não confere.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = ErrorMessage.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Recurso não encontrado.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = ErrorMessage.class
                  )
              )
          )
      }
  )
  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) {
    Usuario user = usuarioService.editarSenha(id, usuarioSenhaDTO.getSenhaAtual(), usuarioSenhaDTO.getNovaSenha(), usuarioSenhaDTO.getConfirmaSenha());
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary="Recuperar todos os usuário.",
      description = "Recurso para recuperar todos os usuário.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Recurso recuperado com sucesso.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(
                      implementation = UsuarioResponseDTO.class
                  )
              )
          )
      }
  )
  @GetMapping
  public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
    List<Usuario> users = usuarioService.buscarTodos();
    return ResponseEntity.ok(UsuarioMapper.toListDto(users));
  }
}
