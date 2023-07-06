package com.example.Site.DTO.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosCadastroCliente(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{8,11}") String senha,
        @NotBlank @Pattern(regexp = "\\d{11}") String telefone,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate nascimento,
        ClienteRole clienteRole) {
}
