import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Tarefa {
    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private int nivelPrioridade;
    private String categoria;
    private String status;

    @Override
    public String toString() {
        return "Tarefa:" + nome + ", Descrição: " + descricao + ", Data de Término: " + dataTermino + ", Prioridade: " + nivelPrioridade + ", Categoria: " + categoria + ", Status: " + status;
    }
}
