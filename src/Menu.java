import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Menu {

    private final Scanner keyboard = new Scanner(System.in);
    private final List<Tarefa> listaTarefas = new ArrayList<>();

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nToDo List");
            System.out.println("1. Criar tarefa.");
            System.out.println("2. Editar progressao da tarefa.");
            System.out.println("3. Editar tarefa.");
            System.out.println("4. Listar tarefas.");
            System.out.println("5. Listar tarefas por prioridade.");
            System.out.println("6. Listar tarefas por categoria.");
            System.out.println("0. Sair.");
            System.out.print("Escolha uma opcao: ");

            opcao = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcao) {
                case 1 -> criarTarefa();
                case 2 -> atualizarStatus();
                case 3 -> editarTarefa();
                case 4 -> listarTarefas();
                case 5 -> listarPorPrioridade();
                case 6 -> listarPorCategoria();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida!");
            }
        }
    }

    private void criarTarefa() {
        System.out.println("\nCriando nova tarefa...");
        System.out.print("Nome: ");
        String nome = keyboard.nextLine();

        System.out.print("Descricao: ");
        String descricao = keyboard.nextLine();

        System.out.print("Data de Termino (AAAA-MM-DD): ");
        String dataString = keyboard.nextLine();
        LocalDate data = LocalDate.parse(dataString);

        System.out.print("Nivel de Prioridade (1 a 5): ");
        int nivelP = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Categoria: ");
        String categoria = keyboard.nextLine();

        Tarefa novaTarefa = new Tarefa(nome, descricao, data, nivelP, categoria, "To Do");
        listaTarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada!");
    }

    private void atualizarStatus() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia, nao ha tarefas para atualizar.");
            return;
        }
        listarTarefas();
        System.out.print("\nDigite o numero da tarefa para mudar o status: ");
        int num = keyboard.nextInt();
        keyboard.nextLine();

        int indice = num - 1;
        if (indice >= 0 && indice < listaTarefas.size()) {
            Tarefa t = listaTarefas.get(indice);
            System.out.println("Status atual: (" + t.getStatus() + ")");

            String novoStatus = lerStatusValido();

            t.setStatus(novoStatus);
            System.out.println("Status da tarefa " + t.getNome() + " atualizado para: " + novoStatus);
        } else {
            System.out.println("Esse numero de tarefa nao existe na lista.");
        }
    }

    private void editarTarefa() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }
        listarTarefas();

        System.out.print("\nDigite o numero da tarefa que deseja editar: ");
        int num = keyboard.nextInt();
        keyboard.nextLine();

        int indice = num - 1;
        if (indice >= 0 && indice < listaTarefas.size()) {
            System.out.println("Editando Tarefa: " + listaTarefas.get(indice).getNome());

            System.out.print("Novo Nome: ");
            String nome = keyboard.nextLine();

            System.out.print("Nova Descricao: ");
            String descricao = keyboard.nextLine();

            System.out.print("Nova Data (AAAA-MM-DD): ");
            String dataString = keyboard.nextLine();
            LocalDate data = LocalDate.parse(dataString);

            System.out.print("Novo Nivel de Prioridade (1-5): ");
            int nivelP = keyboard.nextInt();
            keyboard.nextLine();

            System.out.print("Nova Categoria: ");
            String categoria = keyboard.nextLine();

            String status = lerStatusValido();

            Tarefa tarefaEditada = new Tarefa(nome, descricao, data, nivelP, categoria, status);
            listaTarefas.set(indice, tarefaEditada);
            System.out.println("Tarefa atualizada.");
        } else {
            System.out.println("Indice invalido.");
        }
    }

    private void listarTarefas() {
        System.out.println("\nSuas Tarefas:");
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            for (int i = 0; i < listaTarefas.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + listaTarefas.get(i));
            }
            System.out.println("\nTotal de tarefas: " + listaTarefas.size());
        }
    }

    private void listarPorPrioridade() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia, nada para ordenar.");
        } else {
            listaTarefas.sort((t1, t2) -> Integer.compare(t2.getNivelPrioridade(), t1.getNivelPrioridade()));
            listarTarefas();
        }
    }

    private void listarPorCategoria() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            listaTarefas.sort((t1, t2) -> t1.getCategoria().compareToIgnoreCase(t2.getCategoria()));
            listarTarefas();
        }
    }

    private String lerStatusValido() {
        while (true) {
            System.out.print("Status (To Do, Doing, Done): ");
            String entrada = keyboard.nextLine().trim();

            if (entrada.equalsIgnoreCase("To Do") || entrada.equalsIgnoreCase("ToDo") || entrada.equalsIgnoreCase("Doing") || entrada.equalsIgnoreCase("Done")) {
                if (entrada.equalsIgnoreCase("To Do") || entrada.equalsIgnoreCase("ToDo")) {
                    return "To Do";
                }
                return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase();
            }
            System.out.println("Status invalido! Digite apenas To Do, Doing ou Done.");
        }
    }
}