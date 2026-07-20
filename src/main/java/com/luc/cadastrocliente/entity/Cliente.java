package com.luc.cadastrocliente.entity;

import com.luc.cadastrocliente.entity.dto.cliente.ClienteRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    public Cliente(ClienteRequest request) {
        if(request.clienteId() != null){
            this.id = request.clienteId();
        }else{
            this.id = null;
        }
        this.nome = request.nome();
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.genero = request.genero();
        this.email = request.email();
        this.telefone = request.telefone();
        this.logradouro = request.logradouro();
        this.numeroCasa = request.numeroCasa();
        this.complemento = request.complemento();
        this.bairro = request.bairro();
        this.cidade = request.cidade();
        this.estado = request.estado();
        this.cep = request.cep();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "true")
    @Column(nullable = false, columnDefinition = "boolean")
    private boolean ativo = true;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 3)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 1)
    private Integer genero;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 14)
    private String telefone;

    @Column(nullable = false, length = 255)
    private String logradouro;

    @Column(nullable = false, length = 10)
    private String numeroCasa;

    @Column(length = 255)
    private String complemento;

    @Column(nullable = false, length = 255)
    private String bairro;

    @Column(nullable = false, length = 255)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 9)
    private String cep;
}
