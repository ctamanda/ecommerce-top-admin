package br.com.amanda.ecommercetop.mapper;

import br.com.amanda.ecommercetop.dto.UsuarioRequestDTO;
import br.com.amanda.ecommercetop.dto.UsuarioResponseDTO;
import br.com.amanda.ecommercetop.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setPerfil(dto.perfil());
        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getLogin(),
            usuario.getPerfil()
        );
    }
}