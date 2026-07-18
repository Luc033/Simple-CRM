const url = "http://localhost:8080";

window.addEventListener("DOMContentLoaded", () => {
    const paginaAtual = window.location.pathname;

    if (paginaAtual.endsWith("index.html") || paginaAtual.endsWith("/") || paginaAtual.endsWith("home.html") || paginaAtual.endsWith("home")) {
        carregarListagemClientes()
    }

    if (paginaAtual.endsWith("cadastro-clientes.html")) {
        carregarDadosCliente()
    }
});

async () => {
    addEventListener("submit", (e) => {
        e.preventDefault();
        const parametros = new URLSearchParams(window.location.search);
        const clienteId = parametros.get("id");
        const form = e.target;
        const dados = new FormData(form);
        const clientePreCriado = Object.fromEntries(dados);
        const clienteCriado = {
            "clienteId": clienteId,
            ...clientePreCriado
        };
        console.log(clienteCriado);

        if (clienteId) {
            try {

                const response = await fetch(`${url}/clientes`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(clienteCriado)
                })
                    .then(data => data.json())
                    .catch(err => console.log(err));

                if (response.ok) {
                    mostrarToast("success", "Cliente atualizado com sucesso!");
                    setTimeout(() => {
                        return window.location.href = "index.html"
                    }, 2600);
                }
            } catch (e) {
                mostrarToast("erro", e + "\"VAI SE FODER \" + ");
            }
        }


    })
}


async function carregarListagemClientes() {
    const response = await fetch(`${url}/clientes`)
        .then(res => res.json())
        .then(data => {
            console.log(data);
            return data;
        })
    const tabela = document.getElementById("tblCustomers");
    tabela.innerHTML = "";

    if (response.length == 0) {
        return tabela.innerHTML = "<tr><td colspan='5' class='text-center'>Nenhum cliente encontrado</td></tr>";
    }
    response.forEach(cliente => {
        tabela.innerHTML += `
        <tr>
            <td class="text-capitalize">${cliente.nome}</td>
            <td>${cliente.telefone} - ${cliente.email.toLowerCase()}</td>
            <td>${cliente.cidade.toUpperCase()}/${cliente.estado.toUpperCase()}</td>
            <td>${!cliente.status ? "Ativo" : "Inativo"}</td>
            <td><button class="btn btn-outline-danger rounded" type="button" onclick="abrirModal(0, ${cliente.id})">xxx</button></td>
        </tr>
         `;
    });
}

async function carregarDadosCliente() {
    const parametros = new URLSearchParams(window.location.search);
    const clienteId = parametros.get("id");

    if (!clienteId) {
        return null;
    }

    const getCliente = `${url}/clientes/${clienteId}`;
    const response = await fetch(getCliente)
        .then(res => res.json())
        .then(data => {
            return data;
        })

    if (response.length == 0 || response == null || response == undefined || response == []) {
        alert("Ocorreu um erro ao carregar os dados do cliente.");
        return window.location.redirect = "index.html";
    }

    const campos = [];

    const inputNome = document.getElementById("nome");
    const inputCpf = document.getElementById("cpf");
    const inputDn = document.getElementById("dataNascimento");
    const inputGenero = document.getElementById("genero");
    const inputTelefone = document.getElementById("telefone");
    const inputEmail = document.getElementById("email");
    const inputCep = document.getElementById("cep");
    const inputLogradouro = document.getElementById("logradouro");
    const inputNumero = document.getElementById("numeroCasa");
    const inputComplemento = document.getElementById("complemento");
    const inputBairro = document.getElementById("bairro");
    const inputCidade = document.getElementById("cidade");
    const inputEstado = document.getElementById("estado");

    campos.push(inputNome, inputCpf, inputDn, inputGenero, inputTelefone, inputEmail, inputCep, inputLogradouro, inputNumero, inputComplemento, inputBairro, inputCidade, inputEstado);
    preencherCampos(response, campos);


}

function validarCampo(elem) {
    if (elem.value.length == 0 || elem.value.replace(" ", "").length == 0) {
        elem.classList.add("alert alert-danger");
        return false;
    }
    return true;
}

function preencherCampos(cliente, campos) {
    campos.forEach((campo) => {
        if (cliente[campo.id] != null) {
            campo.value = cliente[campo.id];
        }
    })

}


function mostrarToast(tipo, mensagem) {
    console.log("entrou no toast");
    const elementoToast = document.getElementById("toast");
    const toastBody = document.getElementById("toastBody");
    const toastHeader = document.getElementById("toastHeader");
    const header = elementoToast.querySelector(".toast-header");

    const toast = bootstrap.Toast.getOrCreateInstance(elementoToast, {
        delay: 2000
    });

    toastBody.textContent = mensagem;
    toastHeader.textContent = tipo === "success" ? "Sucesso!" : "Erro!";

    // Remove as classes anteriores
    header.classList.remove(
        "bg-success",
        "bg-danger",
        "text-white"
    );

    // Adiciona a cor conforme o tipo
    if (tipo === "success") {
        header.classList.add("bg-success", "text-white");
    } else {
        header.classList.add("bg-danger", "text-white");
    }

    const indicador = elementoToast.querySelector("span");

    indicador.classList.remove("bg-success", "bg-danger");
    indicador.classList.add(tipo === "success" ? "bg-success" : "bg-danger");

    toast.show();
}