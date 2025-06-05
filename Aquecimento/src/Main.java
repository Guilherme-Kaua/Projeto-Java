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

        while (true){
            persistencia.recuperarCentral();
            System.out.println("1 - nova tarefa\n2 - listar todas as tarefa\n3 – exibir informações de uma tarefa específica\ns - sair");
            String escolha = input.nextLine();
            loop:switch (escolha){
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
                    for (Tarefa valor: persistencia.recuperarCentral().getTodasAsTarefas()){
                        System.out.println(valor);
                    }
                    break;
                case "3":
                    System.out.println("Digite o id da Tarefa que procura");
                    long pesquisa = input.nextLong();
                    for (Tarefa valor: persistencia.recuperarCentral().getTodasAsTarefas()){
                        long id = valor.getId();
                        if (id == pesquisa){
                            System.out.println(valor);
                            break;
                        }
                    }
                    break;
                case "s":
                    break loop;
            }
        }
    }
}