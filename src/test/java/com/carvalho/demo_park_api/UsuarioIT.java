package com.carvalho.demo_park_api;

import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
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
@Import(TestEnvironmentConfig.class)
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
}
