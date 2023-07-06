package com.example.Site.controller;

import com.example.Site.DTO.cliente.DadosCadastroCliente;
import com.example.Site.DTO.cliente.DadosListagemCliente;
import com.example.Site.DTO.security.AuthenticationDTO;
import com.example.Site.DTO.security.CadastroDTO;
import com.example.Site.DTO.security.DadosTokenJWT;
import com.example.Site.domain.cliente.Cliente;
import com.example.Site.domain.usuario.Usuario;
import com.example.Site.repository.ClienteRepository;
import com.example.Site.repository.UsuarioRepository;
import com.example.Site.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var clienteNomeSenha = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.senha());

        var auth = this.authenticationManager.authenticate(clienteNomeSenha);

        var token = this.tokenService.generationToken((Cliente) auth.getPrincipal());

       return ResponseEntity.ok(new DadosTokenJWT(token));

    }
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroDTO cadastroDTO){
        if(this.usuarioRepository.findByEmail(cadastroDTO.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(cadastroDTO.passoword());
            Usuario usuario= new Usuario(cadastroDTO.email(), encryptedPassword, cadastroDTO.usuarioRole());

            this.usuarioRepository.save(usuario);

            return ResponseEntity.ok().body("Usuario Cadastrado");
        }
    }
}
