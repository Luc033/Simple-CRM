package com.luc.cadastrocliente.entity.dto.cliente;

import com.luc.cadastrocliente.entity.Cliente;

import java.time.LocalDate;

public record ClienteResponse(Long clienteId,
                              Boolean ativo,
                              String nome,
                              String cpf,
                              String email,
                              String telefone,
                              LocalDate dataNascimento,
                              Integer genero,
                              String logradouro,
                              String numeroCasa,
                              String complemento,
                              String bairro,
                              String cidade,
                              String estado,
                              String cep
                         ) {

    public ClienteResponse(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.isAtivo(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getDataNascimento(),
                cliente.getGenero(),
                cliente.getLogradouro(),
                cliente.getNumeroCasa(),
                cliente.getComplemento(),
                cliente.getBairro(),
                cliente.getCidade(),
                cliente.getEstado(),
                cliente.getCep()
        );
    }
}