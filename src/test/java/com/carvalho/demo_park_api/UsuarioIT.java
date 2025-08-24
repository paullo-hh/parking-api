package com.carvalho.demo_park_api;

import com.carvalho.demo_park_api.config.TestEnvConfig;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import com.carvalho.demo_park_api.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuario/usuario-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuario/usuario-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(TestEnvConfig.class)
public class UsuarioIT {
  @Autowired
  WebTestClient webTestClient;

  @Test
  public void createUsuario_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201() {
    UsuarioResponseDTO usuarioResponseDTO = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@email.com", "123456"))
        .exchange()
        .expectStatus().isCreated()
        .expectBody(UsuarioResponseDTO.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO).isNotNull();
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getId()).isNotNull();
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getUsername()).isEqualTo("tody@email.com");
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getRole()).isEqualTo("CLIENTE");
  }

  @Test
  public void createUsuario_ComUsernameInvalido_RetornarErrorMessageComStatus422() {
    // username/email inválido
    ErrorMessage responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@", "123456"))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    // username/email vazio
    responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("", "123456"))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    // username/email incompleto
    responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@com", "123456"))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
  }

  @Test
  public void createUsuario_ComPasswordInvalido_RetornarErrorMessageComStatus422() {
    // password vazio
    ErrorMessage responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@email.com", ""))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    // password menor que o requerido
    responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@email.com", "123"))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    // password maior que o requerido
    responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("tody@email.com", "123456789"))
        .exchange()
        .expectStatus().isEqualTo(422)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
  }

  @Test
  public void createUsuario_ComUsernameDuplicado_RetornarErrorMessageComStatus409() {
    ErrorMessage responseBody = webTestClient
        .post()
        .uri("/api/v1/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new UsuarioCreateDTO("ana@email.com", "123456"))
        .exchange()
        .expectStatus().isEqualTo(409)
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
  }

  @Test
  public void findUsuario_ComIdExistente_RetornarUsuarioComStatus200() {
    UsuarioResponseDTO usuarioResponseDTO = webTestClient
        .get()
        .uri("/api/v1/usuarios/100")
        .exchange()
        .expectStatus().isOk()
        .expectBody(UsuarioResponseDTO.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO).isNotNull();
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getId()).isEqualTo(100);
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getUsername()).isEqualTo("ana@email.com");
    org.assertj.core.api.Assertions.assertThat(usuarioResponseDTO.getRole()).isEqualTo("ADMIN");
  }

  @Test
  public void findUsuario_ComIdInexistente_RetornarErrorMessageComStatus404() {
    ErrorMessage errorMessage = webTestClient
        .get()
        .uri("/api/v1/usuarios/0")
        .exchange()
        .expectStatus().isNotFound()
        .expectBody(ErrorMessage.class)
        .returnResult().getResponseBody();

    org.assertj.core.api.Assertions.assertThat(errorMessage).isNotNull();
    org.assertj.core.api.Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
  }
}
