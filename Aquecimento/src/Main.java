import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = new CentralDeInformacoes();

        loop:while (true){
            persistencia.recuperarCentral();
            System.out.println("1 - nova tarefa\n2 - listar todas as tarefa\n3 – exibir informações de uma tarefa específica\n4 – Gerar relatório de tarefas de um dia específico\ns - sair");
            String escolha = input.nextLine();
            switch (escolha){
                case "1":
                    System.out.println("Digite o título");
                    String t = input.nextLine();
                    System.out.println("Digite a descrição");
                    String d = input.nextLine();
                    try{
                        System.out.println("Digite o dia, mês e ano (dia/mês/ano) sem espaços");
                        String[] data = input.nextLine().split("/");
                        Tarefa tarefa = new Tarefa(t,d, LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0])));
                        central.adicionarTarefa(tarefa);
                        persistencia.salvarCentral(central);
                    } catch (Exception e){
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    break;
                case "2":
                    persistencia.recuperarCentral().getTodasAsTarefas();
                    break;
                case "3":
                    System.out.println("Digite o id da Tarefa que procura");
                    try{
                        long pesquisa = input.nextLong();
                        System.out.println(persistencia.recuperarCentral().recuperarTarefaPorId(pesquisa));
                    }
                    catch (Exception e){
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    input.nextLine();
                    break;
                case "4":
                    System.out.println("Digite o dia especifíco das tarefas (dia/mês/ano) sem espaços = ");
                    try{
                        String[] espec = input.nextLine().split("/");
                        GeradorDeRelatorios.obterTarefasDeUmDia(LocalDate.of(Integer.parseInt(espec[2]),Integer.parseInt(espec[1]),Integer.parseInt(espec[0])), persistencia.recuperarCentral());
                    } catch (Exception e){
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                case "s":
                    input.close();
                    System.out.println("Obrigado por usar, saindo...\n");
                    break loop;
                default:
                    System.out.println("Opção Inválida...\n");
                    break;
            }
        }
    }
}