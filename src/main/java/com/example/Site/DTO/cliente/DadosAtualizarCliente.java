package com.example.Site.DTO.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCliente(@NotNull Long id, String email, String telefone) {
}
