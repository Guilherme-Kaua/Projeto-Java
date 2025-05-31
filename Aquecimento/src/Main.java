import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Tarefa tarefa = new Tarefa("oi","Uma tarefa", LocalDate.now());
        CentralDeInformacoes central = new CentralDeInformacoes();
        System.out.println(central.adicionarTarefa(tarefa));
    }
}