const url = "http://localhost:8080";

let paginaListaClientesAtual = 0;

const filtrosListaClientesAtualAtuais = {
    size: 10,
    sort: "nome",
    direction: "asc",
    nome: "",
    ativo: ""
};

window.addEventListener("DOMContentLoaded", () => {
    const paginaAtual = window.location.pathname;

    if (paginaAtual.endsWith("index.html") || paginaAtual.endsWith("/") || paginaAtual.endsWith("home.html") || paginaAtual.endsWith("home")) {
        carregarListagemClientes()
    }

    if (paginaAtual.endsWith("cadastro-clientes.html")) {
        carregarDadosCliente()
    }
});

document.querySelector("#form-cadastro").addEventListener("submit", (e) => {
    e.preventDefault();
    const parametros = new URLSearchParams(window.location.search);
    const clienteId = parametros.get("id");

    if (e.submitter.id === "btnSalvarCadastroCliente") {
        const form = e.target;
        const dados = new FormData(form);

        const clientePreCriado = Object.fromEntries(dados);
        const clienteCriado = {
            "clienteId": clienteId,
            ...clientePreCriado
        };
        console.log("Campo genero: " + clienteCriado.genero);
        console.log("Objeto do formulario pronto para enviar:")
        console.log(clienteCriado);
        salvarCadastroCliente(clienteCriado, clienteId ? "PUT" : "POST");
    }


})


async function carregarListagemClientes() {
    let pagina;
    mostrarLoading(true);
    try {
        pagina = await buscarClientes({
            page: paginaListaClientesAtual,
            ...filtrosListaClientesAtualAtuais
        });
    } catch (erro) {
        mostrarToast(
            "error",
            erro.message ?? "Ocorreu um erro ao buscar os clientes."
        );

        console.error("Erro retornado durante a busca:", erro);
        mostrarLoading(false);
        return;
    }

    try {
        montarTabela(pagina.content);
    } catch (erro) {
        console.error("Erro ao montar a tabela:", erro);
    } finally {
        mostrarLoading(false);
    }
}


async function carregarDadosCliente() {
    const parametros = new URLSearchParams(window.location.search);
    const clienteId = parametros.get("id");

    if (!clienteId) {
        return null;
    }
    mostrarLoading(true);

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
    mostrarLoading(false);

}

function validarCampo(elem) {
    if (elem.value.length == 0 || elem.value.replace(" ", "").length == 0) {
        elem.classList.add("alert alert-danger");
        return false;
    }
    return true;
}

async function preencherCampos(cliente, campos) {
    await campos.forEach((campo) => {

        if (cliente[campo.id] != null) {
            campo.value = cliente[campo.id];
        }
    })

}

async function buscarClientes({
                                  page = 0,
                                  size = 10,
                                  sort = "id",
                                  direction = "desc",
                                  nome = "",
                                  ativo = true,
                                  cidade = "",
                                  estado = ""
                              } = {}) {
    const params = new URLSearchParams();

    params.set("page", page);
    params.set("size", size);
    params.set("sort", sort);
    params.set("direction", direction);

    if (nome) {
        params.set("nome", nome);
    }

    if (ativo !== "") {
        params.set("ativo", ativo);
    }

    if (cidade) {
        params.set("cidade", cidade);
    }

    if (estado) {
        params.set("estado", estado);
    }

    const response = await fetch(
        `/clientes?${params.toString()}`
    );

    const body = await response.json();

    if (!response.ok) {
        console.error(body.message);
        throw new Error(
            body.mensagem ?? "Erro ao buscar clientes"
        );
    }

    return body;
}


function mostrarToast(tipo, mensagem) {
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

async function salvarCadastroCliente(cliente, method) {
    try {
        const response = await fetch(`${url}/clientes`, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cliente)
        });

        // Lê o JSON retornado pelo ControllerAdvice
        const respostaBody = await response.json();

        if (!response.ok) {
            console.error("Erro retornado pela API:", respostaBody);

            mostrarToast(
                "danger",
                respostaBody.message || "Erro ao salvar cliente."
            );

            return;
        }

        mostrarToast(
            "success",
            method === "POST"
                ? "Cliente cadastrado com sucesso!"
                : "Cliente atualizado com sucesso!"
        );

        setTimeout(() => {
            window.location.href = "index.html";
        }, 1600);

    } catch (erro) {
        console.error("Erro de comunicação com a API:", erro);

        mostrarToast(
            "danger",
            "Não foi possível comunicar com o servidor."
        );
    }
}

async function excluirCliente(id) {
    try {
        const confirmou = await confirmarAcao(
            "Excluir cliente",
            "Deseja realmente excluir este cliente?"
        );

        if (!confirmou) {
            return null;
        }
        const res = await fetch(`${url}/clientes/${id}`, {
            method: "DELETE"
        }).then(res => {
            if (res.ok) {
                mostrarToast("success", "Cliente excluido com sucesso!")
                setTimeout(() => {
                    carregarListagemClientes();
                }, 500);
            }
        }).catch(erro => {
            console.error("Erro ao excluir cliente:", erro);
            mostrarToast("error", "Erro ao excluir cliente.");
        })
    } catch (erro) {
        console.error("Erro ao excluir cliente:", erro);
        mostrarToast("error", "Não foi possível comunicar com o servidor.");
    }
}

function proximaPaginaListaClientes() {
    paginaListaClientesAtual++;
    carregarListagemClientes();
}

function mostrarLoading(exibir) {

    if (exibir) {

        if (document.querySelector("#divOverlay")) {
            return;
        }

        const overlay = document.createElement("div");

        overlay.id = "divOverlay";
        overlay.className = "loading-overlay";

        overlay.innerHTML = `
            <div class="d-flex flex-column align-items-center gap-3">

                <div class="spinner-border text-light" role="status">
                    <span class="visually-hidden">Aguarde...</span>
                </div>

                <span class="text-light">
                    Aguarde...
                </span>

            </div>
        `;

        document.body.appendChild(overlay);

        requestAnimationFrame(() => {
            overlay.classList.add("show");
        });

    } else {

        const overlay = document.querySelector("#divOverlay");

        if (!overlay) return;

        overlay.classList.remove("show");

        overlay.addEventListener("transitionend", () => {
            overlay.remove();
        }, { once: true });
    }
}

function confirmarAcao(titulo, mensagem) {
    return new Promise((resolve) => {
        const modalElement = document.getElementById("modalConfirmacao");
        const tituloElement = document.getElementById("modalConfirmacaoTitulo");
        const mensagemElement = document.getElementById("modalConfirmacaoMensagem");
        const btnConfirmar = document.getElementById("btnModalConfirmar");

        const modal = bootstrap.Modal.getOrCreateInstance(modalElement);

        tituloElement.textContent = titulo;
        mensagemElement.textContent = mensagem;

        btnConfirmar.onclick = () => {
            resolve(true);
            modal.hide();
        };

        modalElement.addEventListener(
            "hidden.bs.modal",
            () => resolve(false),
            {once: true}
        );

        modal.show();
    });
}


function montarTabela(clientes) {

    const tabela = document.getElementById("tblCustomers");
    tabela.innerHTML = "";

    if (clientes.length == 0) {
        mostrarLoading(false);
        return tabela.innerHTML = "<tr><td colspan='5' class='text-center'>Nenhum cliente encontrado</td></tr>";
    }
    clientes.forEach(cliente => {
        tabela.innerHTML += `
            
        <tr>
            <td class="text-capitalize td-cortado" style="max-width:">${cliente.nome}</td>
            <td class="td-cortado">${cliente.telefone} <br/> ${cliente.email.toLowerCase()}</td>
            <td class="td-cortado">${cliente.cidade.toUpperCase()}/${cliente.estado.toUpperCase()}</td>
            <td class="text-truncate">${!cliente.status ? "Ativo" : "Inativo"}</td>
            <td class="text-truncate">
            <div class="dropdown position-static">
            <button class="btn btn-outline-dark border-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                <i class=\"bi bi-three-dots-vertical\"></i>
              </button>
              <ul class="dropdown-menu z-3">
                <li>
                    <a class="dropdown-item z-3" href="/cadastro-clientes.html?id=${cliente.clienteId}">
                        <i class="bi bi-pencil-square"></i> Editar
                    </a>
                </li>
                <li>
                    <button type="button" class="dropdown-item btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick=excluirCliente(${cliente.clienteId})>
                        <i class="bi bi-trash-fill"></i>
                        Excluir
                    </button>
                </li>
              </ul>
                </div>
         </td>
        </tr>
         `;
    });
    mostrarLoading(false);
}