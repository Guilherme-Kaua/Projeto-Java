import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CentralDeInformacoes{
    ArrayList<Tarefa> todasAsTarefas = new ArrayList<Tarefa>();

    public boolean adicionarTarefa(Tarefa obj){
        for (Tarefa valor: todasAsTarefas) {
            if (valor.equals(obj)) {
                return false;
            }
        }
        todasAsTarefas.add(obj);
        return true;
    }
}
