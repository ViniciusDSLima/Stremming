package com.example.Site.controller;

import com.example.Site.DTO.video.DadosDetalhamentoVideo;
import com.example.Site.DTO.video.DadosAtualizarVideo;
import com.example.Site.DTO.video.DadosCadastroVideo;
import com.example.Site.domain.video.Video;
import com.example.Site.repository.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("inicial")
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postarVideo(@RequestBody @Valid DadosCadastroVideo dadosCadastroVideo, UriComponentsBuilder uriComponentsBuilder){
        var videoCadastrado = videoRepository.save(new Video(dadosCadastroVideo));

        var uri = uriComponentsBuilder.path("videos/{id}").buildAndExpand(videoCadastrado).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarVideo(@RequestBody @Valid DadosAtualizarVideo dadosAtualizarVideo){
        var video = videoRepository.getReferenceById(dadosAtualizarVideo.id());
        video.atualizarInformacoes(dadosAtualizarVideo);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoVideo>> buscarTodosVideos(@PageableDefault(size = 30, sort = {"genero"})Pageable pagina){
        var video = videoRepository.findAllByAtivoTrue(pagina).map(DadosDetalhamentoVideo::new);

        return ResponseEntity.ok(video);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarVideoById(@PathVariable Long id){
        var video = videoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity apagarVideo(@PathVariable Long id){
        var video = videoRepository.getReferenceById(id);
        video.excluir();

        return ResponseEntity.noContent().build();
    }
}
