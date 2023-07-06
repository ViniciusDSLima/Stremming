package com.example.Site.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private UsuarioRole role;

    public Usuario(String email, String senha, UsuarioRole usuarioRole) {
        this.email = email;
        this.password = senha;
        this.role = usuarioRole;
    }
}
