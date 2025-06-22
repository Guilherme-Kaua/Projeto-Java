import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = new CentralDeInformacoes();

        loop:while (true){
            persistencia.recuperarCentral();
            System.out.println("1 - nova tarefa\n2 - listar todas as tarefa\n3 – exibir informações de uma tarefa específica\ns - sair");
            String escolha = input.nextLine();
            switch (escolha){
                case "1":
                    System.out.println("Digite o título");
                    String t = input.nextLine();
                    System.out.println("Digite a descrição");
                    String d = input.nextLine();
                    System.out.println("Digite o dia, mês e ano (ano/mês/dia) sem espaços");
                    String[] data = input.nextLine().split("/");
                    Tarefa tarefa = new Tarefa(t,d, LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])));
                    central.adicionarTarefa(tarefa);
                    persistencia.salvarCentral(central);
                    break;
                case "2":
                    persistencia.recuperarCentral().getTodasAsTarefas();
                    break;
                case "3":
                    System.out.println("Digite o id da Tarefa que procura");
                    long pesquisa = input.nextLong();
                    System.out.println(persistencia.recuperarCentral().recuperarTarefaPorId(pesquisa));
                    input.nextLine();
                    break;
                case "s":
                    System.out.println("Obrigado por usar, saindo...");
                    break loop;
            }
            GeradorDeRelatorios.obterTarefasDeUmDia(LocalDate.now(), central);
        }
    }
}