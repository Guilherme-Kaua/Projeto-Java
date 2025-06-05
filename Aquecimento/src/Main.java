import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.println("1 - nova tarefa\n2 - listar todas as tarefa\n3 – exibir informações de uma tarefa específica\nS - sair”)");
            String escolha = input.nextLine();
            switch (escolha){
                case "1":
                    System.out.println("Digite o título");
                    String t = input.nextLine();
                    System.out.println("Digite a descrição");
                    String d = input.nextLine();
                    System.out.println("Digite o dia, mês e ano (ano/mês/dia) sem espaços");
                    String[] data = input.nextLine().split("\\\\");
                    Tarefa tarefa = new Tarefa(t,d, LocalDate.of(Integer.parseInt(data[0],Integer.parseInt(data[1],Integer.parseInt(data[2]);

            }
        }
    }
}