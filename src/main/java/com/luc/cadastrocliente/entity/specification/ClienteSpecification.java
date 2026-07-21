package com.luc.cadastrocliente.entity.specification;

import com.luc.cadastrocliente.entity.Cliente;
import com.luc.cadastrocliente.entity.dto.cliente.ClienteFiltro;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    private ClienteSpecification() {
    }

    public static Specification<Cliente> comFiltros(ClienteFiltro filtro) {

        return (root, query, criteriaBuilder) -> {

            var predicates = criteriaBuilder.conjunction();

            if (filtro.nome() != null && !filtro.nome().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nome")),
                                "%" + filtro.nome().toLowerCase() + "%"
                        )
                );
            }

            if (filtro.cpf() != null && !filtro.cpf().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                root.get("cpf"),
                                filtro.cpf()
                        )
                );
            }

            if (filtro.email() != null && !filtro.email().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("email")),
                                "%" + filtro.email().toLowerCase() + "%"
                        )
                );
            }
            if (filtro.telefone() != null && !filtro.telefone().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("telefone")),
                                "%" + filtro.telefone().toLowerCase() + "%"
                        )
                );
            }

            if (filtro.ativo() != null) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                root.get("ativo"),
                                filtro.ativo()
                        )
                );
            }

            if (filtro.genero() != null) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                root.get("genero"),
                                filtro.genero()
                        )
                );
            }

            if (filtro.cidade() != null && !filtro.cidade().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("cidade")),
                                "%" + filtro.cidade().toLowerCase() + "%"
                        )
                );
            }

            if (filtro.estado() != null && !filtro.estado().isBlank()) {
                predicates = criteriaBuilder.and(
                        predicates,
                        criteriaBuilder.equal(
                                criteriaBuilder.upper(root.get("estado")),
                                filtro.estado().toUpperCase()
                        )
                );
            }

            return predicates;
        };
    }
}