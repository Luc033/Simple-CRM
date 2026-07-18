package com.luc.cadastrocliente.entity.dto.cliente;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteRequest(
        Long clienteId,

        @NotBlank(message = "Nome do cliente é obrigatório.")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @NotBlank(message = "CPF do cliente é obrigatório.")
        @Size(min = 11, max = 11, message = "CPF deve ter no máximo 11 caracteres.")
        String cpf,

        @Email(message = "Um email válido deve ser informado.")
        String email,

        @NotBlank(message = "Telefone é obrigatório.")
        @Size(min = 11)
        String telefone,

        @NotNull(message = "Data de nascimento é obrigatório")
        LocalDate dataNascimento,

        @NotNull(message = "Genero é obrigatório.")
        @Min(value = 0, message = "Gênero inválido")
        @Max(value = 3, message = "Gênero inválido")
        Integer genero,

        @NotBlank
        String logradouro,

        @NotBlank
        String numeroCasa,
        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        @Size(min = 2, max = 2)
        String estado,

        @NotBlank(message = "CEP é obrigatório")
        @Size(min = 8, max = 8)
        String cep
) {
}