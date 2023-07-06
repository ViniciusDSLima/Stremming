package com.example.Site.DTO.video;

import com.example.Site.domain.video.Genero;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroVideo(@NotBlank String titulo, @NotNull Genero genero, @NotBlank String sinopse, @NotBlank String duracao, @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate lancamento) {
}
