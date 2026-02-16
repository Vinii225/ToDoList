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
            System.out.println("2. Listar tarefas.");
            System.out.println("3. Listar tarefas por prioridade.");
            System.out.println("4. Listar tarefas por categoria.");
            System.out.println("0. Sair.");
            System.out.print("Escolha uma opção: ");

            opcao = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcao) {
                case 1 -> criarTarefa();
                case 2 -> listarTarefas();
                case 3 -> listarPorPrioridade();
                case 4 -> listarPorCategoria();
                case 0 -> System.out.println("Saindo... Até mais!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void criarTarefa() {
        System.out.println("\nCriando nova tarefa...");
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
        System.out.println("Tarefa adicionada!");
    }

    private void listarTarefas() {
        System.out.println("\nSuas Tarefas:");
        if (listaTarefas.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            for (int i = 0; i < listaTarefas.size(); i++) {
                System.out.println("("+ (i+1) + ") " + listaTarefas.get(i));
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
}