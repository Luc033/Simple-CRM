package com.luc.cadastrocliente.entity.dto.cliente;

public record ClienteFiltro(
        String nome,
        String cpf,
        String email,
        String telefone,
        Boolean ativo,
        Integer genero,
        String cidade,
        String estado
) {
}
