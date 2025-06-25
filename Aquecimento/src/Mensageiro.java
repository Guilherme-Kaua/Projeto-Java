import java.io.File;
import java.util.Properties;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;



public class Mensageiro {

    public static void enviarEmail(String senhaApp, String remetente, String destinatario) {
        // --- Melhoria: Caminho dinâmico para o arquivo ---
        String homeDir = System.getProperty("user.home");
        File f = new File(homeDir + "\\IdeaProjects\\Pre-Projeto\\Relatorio.pdf");

        // Verifica se o arquivo existe antes de tentar anexar
        if (!f.exists()) {
            System.err.println("Erro: O arquivo de anexo não foi encontrado em " + f.getPath());
            return; // Interrompe a execução se o anexo não existe
        }


        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(f.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Relatório de Projeto");
        attachment.setName(f.getName());

        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setDebug(true); // Mantém o debug para ver os logs detalhados
            email.setHostName("smtp.gmail.com");

// opcional: props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");


            // --- CORREÇÃO DA CONFIGURAÇÃO DE SEGURANÇA ---
            email.setSmtpPort(587); // 1. Define a porta correta para STARTTLS
            email.setAuthentication(remetente, senhaApp); // 2. Usa o email e a SENHA DE APP
            email.setStartTLSEnabled(true);// 3. Habilita o STARTTLS
            // As linhas setSSL(true) e setSslSmtpPort() foram removidas para evitar conflito.

            email.addTo(destinatario);
            email.setFrom(remetente, "Nome do Remetente"); // Boa prática: definir um nome
            email.setSubject("Teste de Envio de Email com Anexo");
            email.setMsg("Olá,\n\nSegue em anexo o relatório conforme solicitado.\n\nAtenciosamente.");

            email.attach(attachment);
            email.send();

            System.out.println("Email enviado com sucesso!");

        } catch (EmailException e) {
            System.err.println("Ocorreu um erro ao enviar o email.");
            e.printStackTrace(); // Imprime o erro detalhado para diagnóstico
        }
    }

    public static void main(String[] args) {
        // IMPORTANTE: A senha aqui deve ser a SENHA DE APP de 16 letras, não a senha da conta.
        Mensageiro.enviarEmail("pccrajhfquiquepefp", "testeemailspdfs@gmail.com", "testeemailspdfs@gmail.com");
    }
}