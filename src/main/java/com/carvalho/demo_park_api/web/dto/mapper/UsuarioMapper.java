package com.carvalho.demo_park_api.web.dto.mapper;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {
  public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
    return new ModelMapper().map(usuarioCreateDTO, Usuario.class);
  }

  public static UsuarioResponseDTO toDto(Usuario usuario) {
    String role = usuario.getRole() == null ? null : usuario.getRole().name().substring("ROLE_".length());

    PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
      @Override
      protected void configure() {
        map().setRole(role);
      }
    };

    ModelMapper mapper = new ModelMapper();
    mapper.addMappings(props);

    return mapper.map(usuario, UsuarioResponseDTO.class);
  }
}
