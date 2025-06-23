import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;


public class Persistencia {

    //cria o objeto xstream para converter outros objetos
    private XStream xstream = new XStream(new DomDriver());

    //cria o arquivo
    private File arquivo = new File("central.xml");

    public void salvarCentral(CentralDeInformacoes central) throws IOException {
        xstream.addPermission(AnyTypePermission.ANY);
        //convertendo objeto em uma String em formato xml
        String xml = xstream.toXML(central);

        //se arquivo não existe, ele cria um
        if(!arquivo.exists()) arquivo.createNewFile();

        //gravar as informaçoes no arquivo
        PrintWriter gravar = new PrintWriter(arquivo);
        gravar.print(xml);
        gravar.close();

    }

    public CentralDeInformacoes recuperarCentral() throws FileNotFoundException {
        xstream.addPermission(AnyTypePermission.ANY);
        if(arquivo.exists()){
            //pega os bytes do arquivo e converte para um objeto do tipo estoque
            FileInputStream fis = new FileInputStream(arquivo);
            //cast para , converte o arquivo em formato xml para o tipo CentraDeInformaçoes
            return (CentralDeInformacoes) xstream.fromXML(fis);
        }
        //se o arquivo nao existir retorna uma nova instancia de estoque
        return new CentralDeInformacoes();
    }


}
