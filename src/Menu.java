import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import ui.Cores;
import ui.Estilo;

public class Menu {

    private final Scanner keyboard = new Scanner(System.in);
    private final List<Tarefa> listaTarefas = new ArrayList<>();

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println(Cores.BLUE + Estilo.BOLD + "\nToDo List" + Cores.RESET);
            System.out.println("1. Criar tarefa.");
            System.out.println("2. Editar APENAS progressão da tarefa.");
            System.out.println("3. Editar tarefa.");
            System.out.println("4. Listar tarefas.");
            System.out.println("5. Listar tarefas por prioridade.");
            System.out.println("6. Listar tarefas por categoria.");
            System.out.println("7. Filtrar tarefas por status.");
            System.out.println("8. Excluir tarefa.");
            System.out.println("0. Sair.");
            System.out.print(Estilo.BOLD + "Escolha uma opção: " + Cores.RESET);

            opcao = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcao) {
                case 1 -> criarTarefa();
                case 2 -> atualizarStatus();
                case 3 -> editarTarefa();
                case 4 -> listarTarefas();
                case 5 -> listarPorPrioridade();
                case 6 -> listarPorCategoria();
                case 7 -> filtrarPorStatus();
                case 8 -> excluirTarefa();
                case 0 -> System.out.println(Cores.YELLOW + "Saindo..." + Cores.RESET);
                default -> System.out.println(Cores.RED + "Opção inválida!" + Cores.RESET);
            }
        }
    }

    private void criarTarefa() {
        System.out.println(Cores.BLUE + "\nCriando nova tarefa..." + Cores.RESET);
        System.out.print("Nome: ");
        String nome = keyboard.nextLine();

        System.out.print("Descrição: ");
        String descricao = keyboard.nextLine();

        System.out.print("Data de Término (AAAA-MM-DD): ");
        String dataString = keyboard.nextLine();
        LocalDate data = LocalDate.parse(dataString);

        System.out.print("Nível de Prioridade (1 a 5): ");
        int nivelP = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Categoria: ");
        String categoria = keyboard.nextLine();

        Tarefa novaTarefa = new Tarefa(nome, descricao, data, nivelP, categoria, "To Do");
        listaTarefas.add(novaTarefa);
        System.out.println(Cores.GREEN + "Tarefa adicionada!" + Cores.RESET);
    }

    private void atualizarStatus() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia, não há tarefas para atualizar." + Cores.RESET);
            return;
        }
        listarTarefas();
        System.out.print(Estilo.BOLD + "\nDigite o número da tarefa para mudar o status: " + Cores.RESET);
        int num = keyboard.nextInt();
        keyboard.nextLine();

        int indice = num - 1;
        if (indice >= 0 && indice < listaTarefas.size()) {
            Tarefa t = listaTarefas.get(indice);
            System.out.println("Status atual: " + Cores.YELLOW + "(" + t.getStatus() + ")" + Cores.RESET);

            String novoStatus = lerStatusValido();

            t.setStatus(novoStatus);
            System.out.println(Cores.GREEN + "Status da tarefa " + t.getNome() + " atualizado para: " + novoStatus + Cores.RESET);
        } else {
            System.out.println(Cores.RED + "Esse número de tarefa não existe na lista." + Cores.RESET);
        }
    }

    private void editarTarefa() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia." + Cores.RESET);
            return;
        }
        listarTarefas();

        System.out.print(Estilo.BOLD + "\nDigite o número da tarefa que deseja editar: " + Cores.RESET);
        int num = keyboard.nextInt();
        keyboard.nextLine();

        int indice = num - 1;
        if (indice >= 0 && indice < listaTarefas.size()) {
            System.out.println(Cores.BLUE + "Editando Tarefa: " + Estilo.UNDERLINE + listaTarefas.get(indice).getNome() + Cores.RESET);

            System.out.print("Novo Nome: ");
            String nome = keyboard.nextLine();

            System.out.print("Nova Descrição: ");
            String descricao = keyboard.nextLine();

            System.out.print("Nova Data (AAAA-MM-DD): ");
            String dataString = keyboard.nextLine();
            LocalDate data = LocalDate.parse(dataString);

            System.out.print("Novo Nível de Prioridade (1-5): ");
            int nivelP = keyboard.nextInt();
            keyboard.nextLine();

            System.out.print("Nova Categoria: ");
            String categoria = keyboard.nextLine();

            String status = lerStatusValido();

            Tarefa tarefaEditada = new Tarefa(nome, descricao, data, nivelP, categoria, status);
            listaTarefas.set(indice, tarefaEditada);
            System.out.println(Cores.GREEN + "Tarefa atualizada." + Cores.RESET);
        } else {
            System.out.println(Cores.RED + "Índice inválido." + Cores.RESET);
        }
    }

    private void listarTarefas() {
        System.out.println(Estilo.BOLD + Estilo.UNDERLINE + "\nSuas Tarefas:" + Cores.RESET);
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia." + Cores.RESET);
        } else {
            int todo = 0, doing = 0, done = 0;

            for (int i = 0; i < listaTarefas.size(); i++) {
                Tarefa t = listaTarefas.get(i);

                String corPrioridade = obterCorPrioridade(t.getNivelPrioridade());

                System.out.print(Cores.BLUE + "(" + (i + 1) + ") " + Cores.RESET);
                System.out.println(corPrioridade + "[Prioridade " + t.getNivelPrioridade() + "] " + Cores.RESET + t);

                if (t.getStatus().equalsIgnoreCase("To Do")) todo++;
                else if (t.getStatus().equalsIgnoreCase("Doing")) doing++;
                else if (t.getStatus().equalsIgnoreCase("Done")) done++;
            }

            System.out.println(Estilo.BOLD + "\nTotal de tarefas: " + listaTarefas.size() + Cores.RESET);
            System.out.println(Cores.RED + "To Do: " + todo + Cores.RESET + " | " +
                    Cores.YELLOW + "Doing: " + doing + Cores.RESET + " | " +
                    Cores.GREEN + "Done: " + done + Cores.RESET);
        }
    }

    private void listarPorPrioridade() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia, nada para ordenar." + Cores.RESET);
        } else {
            listaTarefas.sort((t1, t2) -> Integer.compare(t2.getNivelPrioridade(), t1.getNivelPrioridade()));
            listarTarefas();
        }
    }

    private void listarPorCategoria() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia." + Cores.RESET);
        } else {
            listaTarefas.sort((t1, t2) -> t1.getCategoria().compareToIgnoreCase(t2.getCategoria()));
            listarTarefas();
        }
    }

    private void filtrarPorStatus() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia, nada para filtrar." + Cores.RESET);
            return;
        }

        String busca = lerStatusValido();
        int contador = 0;

        System.out.println(Estilo.BOLD + "\nExibindo tarefas com status: (" + busca + ")" + Cores.RESET);
        for (int i = 0; i < listaTarefas.size(); i++) {
            Tarefa t = listaTarefas.get(i);
            if (t.getStatus().equalsIgnoreCase(busca)) {
                String corPrioridade = obterCorPrioridade(t.getNivelPrioridade());
                System.out.print(Cores.BLUE + "(" + (i + 1) + ") " + Cores.RESET);
                System.out.println(corPrioridade + "[Prioridade " + t.getNivelPrioridade() + "] " + Cores.RESET + t);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println(Cores.RED + "Nenhuma tarefa encontrada com esse status." + Cores.RESET);
        } else {
            System.out.println(Estilo.BOLD + "Total encontrado: " + contador + Cores.RESET);
        }
    }

    private void excluirTarefa() {
        if (listaTarefas.isEmpty()) {
            System.out.println(Cores.YELLOW + "Lista vazia, não há o que excluir." + Cores.RESET);
            return;
        }
        listarTarefas();
        System.out.print(Estilo.BOLD + "\nDigite o número da tarefa que deseja excluir: " + Cores.RESET);
        int num = keyboard.nextInt();
        keyboard.nextLine();

        int indice = num - 1;
        if (indice >= 0 && indice < listaTarefas.size()) {
            Tarefa removida = listaTarefas.remove(indice);
            System.out.println(Cores.RED + "Tarefa '" + removida.getNome() + "' excluída com sucesso!" + Cores.RESET);
        } else {
            System.out.println(Cores.RED + "Índice inválido." + Cores.RESET);
        }
    }

    private String obterCorPrioridade(int nivel) {
        return switch (nivel) {
            case 1 -> Cores.BLUE;
            case 2 -> Cores.GREEN;
            case 3 -> Cores.YELLOW;
            case 4 -> Cores.LARANJA;
            case 5 -> Cores.RED;
            default -> Cores.RESET;
        };
    }

    private String lerStatusValido() {
        while (true) {
            System.out.print(Estilo.BOLD + "Status (To Do, Doing, Done): " + Cores.RESET);
            String entrada = keyboard.nextLine().trim();

            if (entrada.equalsIgnoreCase("To Do") || entrada.equalsIgnoreCase("ToDo") || entrada.equalsIgnoreCase("Doing") || entrada.equalsIgnoreCase("Done")) {
                if (entrada.equalsIgnoreCase("To Do") || entrada.equalsIgnoreCase("ToDo")) {
                    return "To Do";
                }
                return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase();
            }
            System.out.println(Cores.RED + "Status inválido, digite apenas To Do, Doing ou Done." + Cores.RESET);
        }
    }
}