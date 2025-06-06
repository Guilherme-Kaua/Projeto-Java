import java.util.ArrayList;

public class CentralDeInformacoes {
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

    public Tarefa recuperarTarefaPorId(long id) {
        for(Tarefa t: todasAsTarefas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }


    public void getTodasAsTarefas() {
        for (Tarefa valor: todasAsTarefas){
            System.out.println(valor);
        }
    }


    public void setTodasAsTarefas(ArrayList<Tarefa> todasAsTarefas) {
        this.todasAsTarefas = todasAsTarefas;
    }
}
