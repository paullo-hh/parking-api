package com.carvalho.demo_park_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {

  @NotBlank
  @Email(message = "Formato do e-mail é inválido!", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
  private String username;

  @NotBlank
  @Size(min = 6, max = 6)
  private String password;
}
