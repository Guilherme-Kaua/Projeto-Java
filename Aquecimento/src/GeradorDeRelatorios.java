import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

public class GeradorDeRelatorios {
    public static void obterTarefasDeUmDia(LocalDate localDate, CentralDeInformacoes central){
        Document doc = new Document(PageSize.A4);
        try {
            OutputStream os = new FileOutputStream("Relatorio.pdf");
            PdfWriter.getInstance(doc, os);

            doc.open();
            for(Tarefa t: central.todasAsTarefas) {
                if(t.getDeadline().equals(localDate)) {
                    Paragraph pg = new Paragraph(t.toString());
                    doc.add(pg);
                }
            }
            doc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
