package com.example.Site.DTO.video;

import com.example.Site.domain.video.Genero;
import com.example.Site.domain.video.Video;

import java.time.LocalDate;

public record DadosDetalhamentoVideo(String titulo, Genero genero, String sinopse, LocalDate lancamento, String duracao) {

    public DadosDetalhamentoVideo(Video video){
        this(video.getTitulo(),video.getGenero(), video.getSinospse(), video.getLancamento(), video.getDuracao());
    }
}
