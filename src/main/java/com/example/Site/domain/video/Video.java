package com.example.Site.domain.video;

import com.example.Site.DTO.video.DadosAtualizarVideo;
import com.example.Site.DTO.video.DadosCadastroVideo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String sinospse;
    private String duracao;
    private LocalDate lancamento;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private boolean ativo;

    public Video(DadosCadastroVideo dadosCadastroVideo) {
        this.titulo = dadosCadastroVideo.titulo();
        this.duracao = dadosCadastroVideo.duracao();
        this.lancamento = dadosCadastroVideo.lancamento();
        this.sinospse = dadosCadastroVideo.sinopse();
        this.genero = dadosCadastroVideo.genero();
        this.ativo = true;
    }
    public void atualizarInformacoes(DadosAtualizarVideo dadosAtualizarVideo){
        if(dadosAtualizarVideo.titulo() != null){
            this.titulo = dadosAtualizarVideo.titulo();
        }
        if(dadosAtualizarVideo.sinopse() != null){
            this.sinospse = dadosAtualizarVideo.sinopse();
        }
        if(dadosAtualizarVideo.genero() != null){
            this.genero = dadosAtualizarVideo.genero();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
