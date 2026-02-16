import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        List<Tarefa> listaTarefas = new ArrayList<>();

        // 2. Criar as tarefas (usando o construtor do Lombok)
        Tarefa t1 = new Tarefa("Geografia", "Relevo", LocalDate.now(), 1, "Estudos", "ToDo");
        Tarefa t2 = new Tarefa("Java", "Lombok", LocalDate.now(), 5, "Estudos", "ToDo");
        Tarefa t3 = new Tarefa("Limpar quarto", "Geral", LocalDate.now(), 3, "Casa", "ToDo");

        listaTarefas.add(t1);
        listaTarefas.add(t2);
        listaTarefas.add(t3);

        System.out.println("Lista de Tarefas");
        for (Tarefa t : listaTarefas) {
            System.out.println(t);
        }
    }
}