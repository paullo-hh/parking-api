package com.carvalho.demo_park_api.web.dto.mapper;

import com.carvalho.demo_park_api.entity.Usuario;
import com.carvalho.demo_park_api.web.dto.UsuarioCreateDTO;
import com.carvalho.demo_park_api.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

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

  public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios) {
    return usuarios.stream().map(users -> toDto(users)).collect(Collectors.toList());
  }
}
