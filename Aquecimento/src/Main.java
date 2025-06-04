import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        XStream xstream= new XStream(new DomDriver());


        Tarefa tarefa = new Tarefa("oi","Uma tarefa", LocalDate.now());
        CentralDeInformacoes central = new CentralDeInformacoes();
        System.out.println(central.adicionarTarefa(tarefa));
        String x= xstream.toXML(tarefa);
        System.out.println(x);
    }
}