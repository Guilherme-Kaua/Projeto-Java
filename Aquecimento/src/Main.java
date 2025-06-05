import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        XStream xstream= new XStream(new DomDriver());

        Tarefa tarefa = new Tarefa("oi","Uma tarefa", LocalDate.now());
        CentralDeInformacoes central = new CentralDeInformacoes();
        System.out.println(central.adicionarTarefa(tarefa));
        Persistencia persistencia = new Persistencia();
        persistencia.salvarCentral(central);
        System.out.println(persistencia.recuperarCentral());
    }
}