package com.example.Site.DTO.video;

import com.example.Site.domain.video.Genero;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVideo(@NotNull Long id, String titulo, String sinopse, Genero genero) {
}
