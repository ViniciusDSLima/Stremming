package com.example.Site.DTO.cliente;

import com.example.Site.domain.cliente.Cliente;

import java.time.LocalDate;

public record DadosListagemCliente(String nome, String email, LocalDate nascimento, String telefone) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getNome(), cliente.getEmail(), cliente.getNascimento(), cliente.getTelefone());
    }
}
