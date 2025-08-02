import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = new CentralDeInformacoes();

        loop:
        while (true) {
            persistencia.recuperarCentral();
            System.out.println(
                    "1 - nova tarefa\n" +
                            "2 - listar todas as tarefas\n" +
                            "3 - exibir informações de uma tarefa específica\n" +
                            "4 - gerar relatório de tarefas de um dia específico\n" +
                            "5 - enviar email com PDF\n" +
                            "s - sair"
            );

            String escolha = input.nextLine();

            switch (escolha) {
                case "1":
                    System.out.println("Digite o título:");
                    String t = input.nextLine();

                    System.out.println("Digite a descrição:");
                    String d = input.nextLine();

                    try {
                        System.out.println("Digite o dia, mês e ano (dia/mês/ano) sem espaços:");
                        String[] data = input.nextLine().split("/");
                        Tarefa tarefa = new Tarefa(t, d,
                                LocalDate.of(
                                        Integer.parseInt(data[2]),
                                        Integer.parseInt(data[1]),
                                        Integer.parseInt(data[0])
                                )
                        );
                        central.adicionarTarefa(tarefa);
                        persistencia.salvarCentral(central);
                    } catch (Exception e) {
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    break;

                case "2":
                    try{
                        if (persistencia.recuperarCentral().todasAsTarefas.isEmpty()){
                            throw new NullPointerException();
                        }
                        persistencia.recuperarCentral().getTodasAsTarefas();

                    } catch (Exception e) {
                        System.out.println("\nNão tem tarefas disponíveis para mostrar\n");;
                    }

                    break;

                case "3":
                    System.out.println("Digite o ID da tarefa que procura:");
                    try {
                        long pesquisa = input.nextLong();
                        System.out.println(persistencia.recuperarCentral().recuperarTarefaPorId(pesquisa));
                    } catch (Exception e) {
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    input.nextLine(); // Limpa o buffer
                    break;
                case "4":
                    System.out.println("Digite o dia específico das tarefas (dia/mês/ano) sem espaços:");
                    try {
                        String[] espec = input.nextLine().split("/");
                        LocalDate dataRelatorio = LocalDate.of(
                                Integer.parseInt(espec[2]),
                                Integer.parseInt(espec[1]),
                                Integer.parseInt(espec[0])
                        );
                        GeradorDeRelatorios.obterTarefasDeUmDia(dataRelatorio, persistencia.recuperarCentral());
                    } catch (Exception e) {
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    break;
                case "5":

                    System.out.println("Digite o e-mail de destino:");
                    String email = input.nextLine();

                    if (email == null || email.trim().isEmpty()) {
                        System.out.println("E-mail inválido.");
                        break;
                    }

                    System.out.println("Digite a data do relatório (dia/mês/ano):");
                    try {
                        String[] partes = input.nextLine().split("/");
                        LocalDate data = LocalDate.of(
                                Integer.parseInt(partes[2]),
                                Integer.parseInt(partes[1]),
                                Integer.parseInt(partes[0])
                        );

                        Mensageiro mensageiro = new Mensageiro();
                        mensageiro.enviarEmailComPdf(email, data, persistencia.recuperarCentral());
                    } catch (Exception e) {
                        TratarErrosException.imprimirErroFormatado(e);
                    }
                    break;
                case "s":
                    input.close();
                    System.out.println("Obrigado por usar. Saindo...");
                    break loop;

                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }
        }
    }
}
