package com.luc.cadastrocliente.controller;

import com.luc.cadastrocliente.entity.Cliente;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteResponse;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteIdDTO;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteRequest;
import com.luc.cadastrocliente.service.ClienteService;
import jakarta.validation.Valid;
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

    // Testar se o MAP fez a conversão corretamente
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes(){
        var clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes.stream().map(ClienteResponse::new).toList());
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
}
