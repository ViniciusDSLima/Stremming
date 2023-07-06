package com.example.Site.domain.cliente;

import com.example.Site.DTO.cliente.DadosAtualizarCliente;
import com.example.Site.DTO.cliente.DadosCadastroCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    @Column(name = "data_nascimento")
    private LocalDate nascimento;
    @Column(name = "data_cadastro")
    @CreationTimestamp
    private LocalDate dataCadastro;
    private boolean ativo;

    public Cliente(DadosCadastroCliente dadosCadastroCliente) {
        this.nome = dadosCadastroCliente.nome();
        this.email = dadosCadastroCliente.email();
        this.telefone = dadosCadastroCliente.telefone();
        this.senha = dadosCadastroCliente.senha();
        this.nascimento = dadosCadastroCliente.nascimento();
        this.ativo = true;
    }


    public void atualizarInformacoes(DadosAtualizarCliente dadosAtualizarCliente) {
        if (dadosAtualizarCliente.email() != null) {
            this.email = dadosAtualizarCliente.email();
        }
        if (dadosAtualizarCliente.telefone() != null) {
            this.telefone = dadosAtualizarCliente.telefone();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}