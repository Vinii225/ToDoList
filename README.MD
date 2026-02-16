# ToDo List CLI

Sistema de gerenciamento de tarefas desenvolvido em Java com interface estilizada via terminal e escala de prioridades.

## Descrição

Aplicação de linha de comando que permite o gerenciamento completo do ciclo de vida de tarefas (CRUD). O projeto utiliza códigos ANSI para fornecer uma interface visual organizada, facilitando a identificação de urgências e o acompanhamento do progresso geral através de métricas automáticas.

## Funcionalidades

* **Gerenciamento Completo**: Criação, edição, atualização de progresso e exclusão de tarefas.
* **Escala de Prioridade Visual**: Identificação visual de urgência (níveis 1 a 5) com cores específicas:
* Nível 1: Azul.
* Nível 2: Verde.
* Nível 3: Amarelo.
* Nível 4: Laranja.
* Nível 5: Vermelho.


* **Métricas de Progresso**: Contagem dinâmica de tarefas nos estados "To Do", "Doing" e "Done" exibida em tempo real na listagem.
* **Filtros e Ordenação**: Possibilidade de listar por prioridade, categoria ou filtrar resultados por um status específico.

## Estrutura do Projeto

O projeto está organizado para separar a lógica de negócio das definições visuais:

* **Raiz do Projeto**:
* `README.md`: Documentação principal.
* `src/Main.java`: Ponto de entrada da aplicação.
* `src/Menu.java`: Classe principal com a lógica do menu e manipulação da lista.
* `src/Tarefa.java`: Modelo de dados da aplicação.


* **Pacote UI (`src/ui/`)**:
* `Cores.java`: Definições ANSI para cores.
* `Estilo.java`: Definições ANSI para negrito e sublinhado.



## Como Executar

No terminal, dentro da pasta `src`, compile e execute:

```bash
javac ui/*.java Menu.java Main.java
java Main

```
