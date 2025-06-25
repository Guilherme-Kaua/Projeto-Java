import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mensageiro {

    public static void enviarEmail(String senha,String remetente,String destinatario,String msg,String assunto){
        Properties props = new Properties();

        /** Parametros de conexão com o servidor Gmail*/

        props.put("mail.smtp.user", remetente);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.EnableSSL.enable","true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(remetente,senha);
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));

            Address[] toUser = InternetAddress
                    .parse(destinatario);

            message.setRecipients(Message.RecipientType.TO,toUser);
            message.setSubject(assunto);
            message.setText(msg);
            Transport.send(message);

        } catch (MessagingException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        Mensageiro.enviarEmail("lasanha123","testedeemailspdfs@gmail.com","guilhermekauams@gmail.com","EAEEEE","teste teste 123");
    }
}
