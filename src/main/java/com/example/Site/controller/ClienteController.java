package com.example.Site.controller;

import com.example.Site.DTO.cliente.DadosAtualizarCliente;
import com.example.Site.DTO.cliente.DadosCadastroCliente;
import com.example.Site.DTO.cliente.DadosListagemCliente;
import com.example.Site.domain.cliente.Cliente;
import com.example.Site.repository.ClienteRepository;
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
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarClientes(@RequestBody @Valid DadosCadastroCliente dadosCadastroCliente, UriComponentsBuilder uriComponentsBuilder){
        var usuarioCadastrado = clienteRepository.save(new Cliente(dadosCadastroCliente));

        var uri = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuarioCadastrado).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarClientes(@RequestBody @Valid DadosAtualizarCliente dadosAtualizarCliente){
        var usuario = clienteRepository.getReferenceById(dadosAtualizarCliente.id());
        usuario.atualizarInformacoes(dadosAtualizarCliente);

        return ResponseEntity.ok(new DadosListagemCliente(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listarClientes(@PageableDefault(size = 10, sort = {"dataCadastro"})Pageable pageable){
        var listagemUsuarios = clienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemCliente::new);

        return ResponseEntity.ok(listagemUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUsuariosById(@PathVariable Long id){
        var usuario = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemCliente(usuario));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity inativarUsuario(@PathVariable Long id){
        var usuario = clienteRepository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }
}
