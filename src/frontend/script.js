const todoForm = document.getElementById('todo-form');
const listaTarefasContainer = document.getElementById('lista-tarefas');
const btnSalvar = document.getElementById('btn-salvar');
const inputEditIndex = document.getElementById('edit-index');

let tarefas = [];

todoForm.addEventListener('submit', function(event) {
    event.preventDefault();
    salvarTarefa();
});

function salvarTarefa() {
    const editIndex = parseInt(inputEditIndex.value);

    const novaTarefa = {
        nome: document.getElementById('nome').value,
        desc: document.getElementById('descricao').value,
        data: document.getElementById('data').value,
        prioridade: document.getElementById('prioridade').value,
        categoria: document.getElementById('categoria').value,
        status: "To Do"
    };

    if (editIndex === -1) {
        tarefas.push(novaTarefa);
    } else {
        tarefas[editIndex] = novaTarefa;
        inputEditIndex.value = "-1";
        btnSalvar.innerText = "Salvar Tarefa";
        btnSalvar.style.backgroundColor = "#007bff";
    }

    todoForm.reset();
    renderizar();
}

function renderizar() {
    listaTarefasContainer.innerHTML = '';

    tarefas.forEach((t, i) => {
        const card = document.createElement('div');
        card.className = 'tarefa-card';
        card.innerHTML = `
            <strong>${t.nome}</strong> [${t.categoria}]
            <p style="margin: 5px 0; font-size: 0.9em; color: #bbb;">${t.desc}</p>
            <small>Prazo: ${t.data} | Prioridade: ${t.prioridade}</small>
            <div style="margin-top: 10px; display: flex; gap: 8px;">
                <button onclick="prepararEdicao(${i})" style="background: #f39c12; padding: 4px 8px; font-size: 0.8em;">Editar</button>
                <button onclick="excluirTarefa(${i})" style="background: #c0392b; padding: 4px 8px; font-size: 0.8em;">Excluir</button>
            </div>
        `;
        listaTarefasContainer.appendChild(card);
    });
}

function excluirTarefa(index) {
    if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
        tarefas.splice(index, 1);
        renderizar();
    }
}

function prepararEdicao(index) {
    const t = tarefas[index];
    document.getElementById('nome').value = t.nome;
    document.getElementById('descricao').value = t.desc;
    document.getElementById('data').value = t.data;
    document.getElementById('prioridade').value = t.prioridade;
    document.getElementById('categoria').value = t.categoria;

    inputEditIndex.value = index;
    btnSalvar.innerText = "Atualizar Tarefa";
    btnSalvar.style.backgroundColor = "#f39c12";

    // Scroll suave para o topo para facilitar a edição
    window.scrollTo({ top: 0, behavior: 'smooth' });
}