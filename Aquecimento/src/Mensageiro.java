import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import java.io.File;
import java.time.LocalDate;

public class Mensageiro {
    public void enviarEmailComPdf(String email) {
        // Configuração do servidor SMTP
        String host = "smtp.gmail.com";
        final String username = "testedeemailspdfs@gmail.com";
        final String password = "pccrajhfqiqepefp"; // (não deixe isso hardcoded em produção)

        // Destinatário
        String to = email;
        System.out.println("Enviando para: " + to); // Debug

        // Data de Hoje
        LocalDate data = LocalDate.now();

        // Configuração das propriedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Cria a sessão
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        try {
            // Cria a mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Seu PDF - " + data);

            // Parte do texto
            MimeBodyPart textoParte = new MimeBodyPart();
            textoParte.setText("Olá! Segue em anexo o PDF solicitado.");

            // Parte do anexo
            MimeBodyPart anexoParte = new MimeBodyPart();
            anexoParte.attachFile(new File("C:\\Users\\js185\\Documents\\arquivo.pdf"));


            // Junta tudo
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textoParte);
            multipart.addBodyPart(anexoParte);

            message.setContent(multipart);

            // Envia o e-mail
            Transport.send(message);
            System.out.println("Email enviado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
