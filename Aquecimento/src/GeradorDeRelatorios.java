import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeRelatorios {

    public static void obterTarefasDeUmDia(LocalDate data, CentralDeInformacoes central){

        int contador = 0;
        for (Tarefa tarefa: central.todasAsTarefas){
            if (tarefa.getDeadline().equals(data)){
                contador++;
            }
        }
        if (contador == 0){
            System.out.println("Não existe nenhuma tarefa em "+data+", por isso nenhum PDF foi gerado");
            return;
        }

        Document doc = new Document(PageSize.A4);
        try {
            OutputStream os = new FileOutputStream("Relatorio.pdf");

            PdfWriter.getInstance(doc, os);

            doc.open();
            for(Tarefa t: central.todasAsTarefas) {
                if(t.getDeadline().equals(data)) {
                    Paragraph pg = new Paragraph(t.toString());
                    doc.add(pg);
                }
            }
            doc.close();

        } catch (Exception e){
            System.out.println("Um erro inesperado aconteceu");
            e.printStackTrace();
        }
    }
}
