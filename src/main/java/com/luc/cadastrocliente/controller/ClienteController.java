package com.luc.cadastrocliente.controller;

import com.luc.cadastrocliente.entity.Cliente;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteFiltro;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteResponse;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteIdDTO;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteRequest;
import com.luc.cadastrocliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponse> listarCliente(@PathVariable Long id){
        var cliente = clienteService.findById(id);
        return ResponseEntity.ok(new ClienteResponse(cliente));
    }

//    // Testar se o MAP fez a conversão corretamente
//    @GetMapping
//    public ResponseEntity<List<ClienteResponse>> listarClientes(){
//        var clientes = clienteService.findAll();
//        return ResponseEntity.ok(clientes.stream().map(ClienteResponse::new).toList());
//    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            ClienteFiltro filtro
    ) {
        int numeroPagina = Math.max(page, 0);
        int tamanhoPagina = Math.min(Math.max(size, 1), 100);

        String campoOrdenacao = validarCampoOrdenacao(sort);

        Sort.Direction direcao =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                numeroPagina,
                tamanhoPagina,
                Sort.by(direcao, campoOrdenacao)
        );

        Page<ClienteResponse> resultado =
                clienteService.listar(filtro, pageable);

        return ResponseEntity.ok(resultado);
    }


    @PostMapping
    public ResponseEntity<ClienteIdDTO> salvarCliente(@Valid @RequestBody ClienteRequest clienteRequest){
        Cliente cliente = new Cliente(clienteRequest);

        var clienteId = clienteService.save(cliente);

        return ResponseEntity.ok(new ClienteIdDTO(clienteId));

    }
    @PutMapping
    public ResponseEntity<ClienteIdDTO> atualizarCliente(@Valid @RequestBody ClienteRequest request){
        var clienteId = clienteService.save(new Cliente(request));
        return ResponseEntity.ok(new ClienteIdDTO(clienteId));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    private String validarCampoOrdenacao(String sort) {

        return switch (sort) {
            case "id",
                 "nome",
                 "cpf",
                 "email",
                 "telefone",
                 "dataNascimento",
                 "cidade",
                 "estado",
                 "ativo" -> sort;

            default -> "nome";
        };
    }
}
