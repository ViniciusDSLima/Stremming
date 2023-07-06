package com.example.Site.DTO.security;

import com.example.Site.domain.usuario.UsuarioRole;

public record CadastroDTO(String email, String passoword, UsuarioRole usuarioRole) {
}
