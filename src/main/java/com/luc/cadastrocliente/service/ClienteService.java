package com.luc.cadastrocliente.service;

import com.luc.cadastrocliente.entity.Cliente;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteFiltro;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteResponse;
import com.luc.cadastrocliente.entity.specification.ClienteSpecification;
import com.luc.cadastrocliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Long save(Cliente cliente) {
        if (cliente.getId() != null) {
            findById(cliente.getId());
            Cliente clienteSalvo = clienteRepository.save(cliente);
            return clienteSalvo.getId();
        }
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo.getId();
    }

    public Cliente findById(Long id) {
        if (clienteRepository.existsById(id)) {
            return clienteRepository.findById(id).get();
        }

        throw new EntityNotFoundException("Cliente não encontrado: " + id);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<ClienteResponse> listar(ClienteFiltro filtro, Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(ClienteSpecification.comFiltros(filtro), pageable);

        return clientes.map(ClienteResponse::new);
    }

    @Transactional
    public void deleteById(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Cliente não encontrado: " + id);
        }
    }
}
