package com.luc.cadastrocliente.config;

import com.luc.cadastrocliente.entity.Cliente;
import com.luc.cadastrocliente.repository.ClienteRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
public class ClienteSeed implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public ClienteSeed(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) {

        if (clienteRepository.count() > 0) {
            return;
        }

        clienteRepository.saveAll(List.of(

                criarCliente(
                        "Lucas Ricardo",
                        "12345678901",
                        "lucas@email.com",
                        "41999990001",
                        LocalDate.of(1998, 5, 12),
                        0,
                        "Rua das Flores",
                        "125",
                        "Apto 201",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80000-001"
                ),

                criarCliente(
                        "Maria Oliveira",
                        "12345678902",
                        "maria@email.com",
                        "41999990002",
                        LocalDate.of(1995, 8, 23),
                        1,
                        "Rua XV de Novembro",
                        "80",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80020-310"
                ),

                criarCliente(
                        "João Pedro Santos",
                        "12345678903",
                        "joao@email.com",
                        "41999990003",
                        LocalDate.of(1989, 3, 15),
                        0,
                        "Av. República Argentina",
                        "1450",
                        "",
                        "Portão",
                        "Curitiba",
                        "PR",
                        "80610-260"
                ),

                criarCliente(
                        "Fernanda Alves",
                        "12345678904",
                        "fernanda@email.com",
                        "41999990004",
                        LocalDate.of(1997, 7, 30),
                        1,
                        "Rua Itupava",
                        "310",
                        "",
                        "Alto da XV",
                        "Curitiba",
                        "PR",
                        "80045-140"
                ),

                criarCliente(
                        "Carlos Henrique",
                        "12345678905",
                        "carlos@email.com",
                        "41999990005",
                        LocalDate.of(1988, 2, 10),
                        0,
                        "Rua Marechal Deodoro",
                        "980",
                        "Fundos",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80010-010"
                ),

                criarCliente(
                        "Patrícia Gomes",
                        "12345678906",
                        "patricia@email.com",
                        "41999990006",
                        LocalDate.of(1994, 4, 25),
                        1,
                        "Rua Visconde de Guarapuava",
                        "1520",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-900"
                ),

                criarCliente(
                        "Bruno Ferreira",
                        "12345678907",
                        "bruno@email.com",
                        "41999990007",
                        LocalDate.of(1991, 9, 18),
                        0,
                        "Rua Padre Anchieta",
                        "1120",
                        "",
                        "Bigorrilho",
                        "Curitiba",
                        "PR",
                        "80730-000"
                ),

                criarCliente(
                        "Juliana Martins",
                        "12345678908",
                        "juliana@email.com",
                        "41999990008",
                        LocalDate.of(1996, 2, 27),
                        1,
                        "Rua Desembargador Motta",
                        "640",
                        "",
                        "Mercês",
                        "Curitiba",
                        "PR",
                        "80430-200"
                ),

                criarCliente(
                        "Rafael Costa",
                        "12345678909",
                        "rafael@email.com",
                        "41999990009",
                        LocalDate.of(1987, 12, 2),
                        0,
                        "Rua Nunes Machado",
                        "890",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80250-000"
                ),

                criarCliente(
                        "Amanda Ribeiro",
                        "12345678910",
                        "amanda@email.com",
                        "41999990010",
                        LocalDate.of(1993, 10, 8),
                        1,
                        "Rua Conselheiro Laurindo",
                        "210",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-100"
                ),

                criarCliente(
                        "Diego Almeida",
                        "12345678911",
                        "diego@email.com",
                        "41999990011",
                        LocalDate.of(1985, 6, 14),
                        0,
                        "Rua Tibagi",
                        "550",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-110"
                ),

                criarCliente(
                        "Camila Barbosa",
                        "12345678912",
                        "camila@email.com",
                        "41999990012",
                        LocalDate.of(2000, 1, 9),
                        1,
                        "Rua Augusto Stresser",
                        "875",
                        "Casa",
                        "Juvevê",
                        "Curitiba",
                        "PR",
                        "80040-310"
                ),

                criarCliente(
                        "Thiago Mendes",
                        "12345678913",
                        "thiago@email.com",
                        "41999990013",
                        LocalDate.of(1992, 8, 19),
                        0,
                        "Rua Bom Jesus",
                        "430",
                        "",
                        "Cabral",
                        "Curitiba",
                        "PR",
                        "80035-010"
                ),

                criarCliente(
                        "Larissa Rocha",
                        "12345678914",
                        "larissa@email.com",
                        "41999990014",
                        LocalDate.of(1998, 11, 30),
                        1,
                        "Rua São Francisco",
                        "120",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80510-120"
                ),

                criarCliente(
                        "Eduardo Nunes",
                        "12345678915",
                        "eduardo@email.com",
                        "41999990015",
                        LocalDate.of(1986, 7, 21),
                        0,
                        "Rua José Loureiro",
                        "980",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80010-000"
                ),

                criarCliente(
                        "Vanessa Castro",
                        "12345678916",
                        "vanessa@email.com",
                        "41999990016",
                        LocalDate.of(1997, 5, 16),
                        1,
                        "Rua Chile",
                        "350",
                        "",
                        "Rebouças",
                        "Curitiba",
                        "PR",
                        "80220-180"
                ),

                criarCliente(
                        "Ricardo Moreira",
                        "12345678917",
                        "ricardo@email.com",
                        "41999990017",
                        LocalDate.of(1990, 3, 4),
                        0,
                        "Rua Alferes Poli",
                        "470",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80230-090"
                ),

                criarCliente(
                        "Gabriela Freitas",
                        "12345678918",
                        "gabriela@email.com",
                        "41999990018",
                        LocalDate.of(1995, 12, 11),
                        1,
                        "Rua Brigadeiro Franco",
                        "1800",
                        "Apto 52",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80250-030"
                ),

                criarCliente(
                        "Leonardo Azevedo",
                        "12345678919",
                        "leonardo@email.com",
                        "41999990019",
                        LocalDate.of(1984, 9, 28),
                        0,
                        "Rua Mateus Leme",
                        "2150",
                        "",
                        "São Francisco",
                        "Curitiba",
                        "PR",
                        "80530-010"
                ),

                criarCliente(
                        "Beatriz Carvalho",
                        "12345678920",
                        "beatriz@email.com",
                        "41999990020",
                        LocalDate.of(1999, 6, 17),
                        1,
                        "Rua João Gualberto",
                        "780",
                        "",
                        "Alto da Glória",
                        "Curitiba",
                        "PR",
                        "80030-000"
                ),
                criarCliente(
                        "Lucas Ricardo",
                        "12345678901",
                        "lucas@email.com",
                        "41999990001",
                        LocalDate.of(1998, 5, 12),
                        0,
                        "Rua das Flores",
                        "125",
                        "Apto 201",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80000-001"
                ),

                criarCliente(
                        "Maria Oliveira",
                        "12345678902",
                        "maria@email.com",
                        "41999990002",
                        LocalDate.of(1995, 8, 23),
                        1,
                        "Rua XV de Novembro",
                        "80",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80020-310"
                ),

                criarCliente(
                        "João Pedro Santos",
                        "12345678903",
                        "joao@email.com",
                        "41999990003",
                        LocalDate.of(1989, 3, 15),
                        0,
                        "Av. República Argentina",
                        "1450",
                        "",
                        "Portão",
                        "Curitiba",
                        "PR",
                        "80610-260"
                ),

                criarCliente(
                        "Fernanda Alves",
                        "12345678904",
                        "fernanda@email.com",
                        "41999990004",
                        LocalDate.of(1997, 7, 30),
                        1,
                        "Rua Itupava",
                        "310",
                        "",
                        "Alto da XV",
                        "Curitiba",
                        "PR",
                        "80045-140"
                ),

                criarCliente(
                        "Carlos Henrique",
                        "12345678905",
                        "carlos@email.com",
                        "41999990005",
                        LocalDate.of(1988, 2, 10),
                        0,
                        "Rua Marechal Deodoro",
                        "980",
                        "Fundos",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80010-010"
                ),

                criarCliente(
                        "Patrícia Gomes",
                        "12345678906",
                        "patricia@email.com",
                        "41999990006",
                        LocalDate.of(1994, 4, 25),
                        1,
                        "Rua Visconde de Guarapuava",
                        "1520",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-900"
                ),

                criarCliente(
                        "Bruno Ferreira",
                        "12345678907",
                        "bruno@email.com",
                        "41999990007",
                        LocalDate.of(1991, 9, 18),
                        0,
                        "Rua Padre Anchieta",
                        "1120",
                        "",
                        "Bigorrilho",
                        "Curitiba",
                        "PR",
                        "80730-000"
                ),

                criarCliente(
                        "Juliana Martins",
                        "12345678908",
                        "juliana@email.com",
                        "41999990008",
                        LocalDate.of(1996, 2, 27),
                        1,
                        "Rua Desembargador Motta",
                        "640",
                        "",
                        "Mercês",
                        "Curitiba",
                        "PR",
                        "80430-200"
                ),

                criarCliente(
                        "Rafael Costa",
                        "12345678909",
                        "rafael@email.com",
                        "41999990009",
                        LocalDate.of(1987, 12, 2),
                        0,
                        "Rua Nunes Machado",
                        "890",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80250-000"
                ),

                criarCliente(
                        "Amanda Ribeiro",
                        "12345678910",
                        "amanda@email.com",
                        "41999990010",
                        LocalDate.of(1993, 10, 8),
                        1,
                        "Rua Conselheiro Laurindo",
                        "210",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-100"
                ),

                criarCliente(
                        "Diego Almeida",
                        "12345678911",
                        "diego@email.com",
                        "41999990011",
                        LocalDate.of(1985, 6, 14),
                        0,
                        "Rua Tibagi",
                        "550",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80060-110"
                ),

                criarCliente(
                        "Camila Barbosa",
                        "12345678912",
                        "camila@email.com",
                        "41999990012",
                        LocalDate.of(2000, 1, 9),
                        1,
                        "Rua Augusto Stresser",
                        "875",
                        "Casa",
                        "Juvevê",
                        "Curitiba",
                        "PR",
                        "80040-310"
                ),

                criarCliente(
                        "Thiago Mendes",
                        "12345678913",
                        "thiago@email.com",
                        "41999990013",
                        LocalDate.of(1992, 8, 19),
                        0,
                        "Rua Bom Jesus",
                        "430",
                        "",
                        "Cabral",
                        "Curitiba",
                        "PR",
                        "80035-010"
                ),

                criarCliente(
                        "Larissa Rocha",
                        "12345678914",
                        "larissa@email.com",
                        "41999990014",
                        LocalDate.of(1998, 11, 30),
                        1,
                        "Rua São Francisco",
                        "120",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80510-120"
                ),

                criarCliente(
                        "Eduardo Nunes",
                        "12345678915",
                        "eduardo@email.com",
                        "41999990015",
                        LocalDate.of(1986, 7, 21),
                        0,
                        "Rua José Loureiro",
                        "980",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80010-000"
                ),

                criarCliente(
                        "Vanessa Castro",
                        "12345678916",
                        "vanessa@email.com",
                        "41999990016",
                        LocalDate.of(1997, 5, 16),
                        1,
                        "Rua Chile",
                        "350",
                        "",
                        "Rebouças",
                        "Curitiba",
                        "PR",
                        "80220-180"
                ),

                criarCliente(
                        "Ricardo Moreira",
                        "12345678917",
                        "ricardo@email.com",
                        "41999990017",
                        LocalDate.of(1990, 3, 4),
                        0,
                        "Rua Alferes Poli",
                        "470",
                        "",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80230-090"
                ),

                criarCliente(
                        "Gabriela Freitas",
                        "12345678918",
                        "gabriela@email.com",
                        "41999990018",
                        LocalDate.of(1995, 12, 11),
                        1,
                        "Rua Brigadeiro Franco",
                        "1800",
                        "Apto 52",
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80250-030"
                ),

                criarCliente(
                        "Leonardo Azevedo",
                        "12345678919",
                        "leonardo@email.com",
                        "41999990019",
                        LocalDate.of(1984, 9, 28),
                        0,
                        "Rua Mateus Leme",
                        "2150",
                        "",
                        "São Francisco",
                        "Curitiba",
                        "PR",
                        "80530-010"
                ),

                criarCliente(
                        "Beatriz Carvalho",
                        "12345678920",
                        "beatriz@email.com",
                        "41999990020",
                        LocalDate.of(1999, 6, 17),
                        1,
                        "Rua João Gualberto",
                        "780",
                        "",
                        "Alto da Glória",
                        "Curitiba",
                        "PR",
                        "80030-000"
                )

        ));

        System.out.println("Seed de clientes executado com sucesso.");
    }

    private Cliente criarCliente(
            String nome,
            String cpf,
            String email,
            String telefone,
            LocalDate nascimento,
            Integer genero,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep
    ) {

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setDataNascimento(nascimento);
        cliente.setGenero(genero);
        cliente.setLogradouro(logradouro);
        cliente.setNumeroCasa(numero);
        cliente.setComplemento(complemento);
        cliente.setBairro(bairro);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setCep(cep);
        cliente.setAtivo(true);

        return cliente;
    }
}