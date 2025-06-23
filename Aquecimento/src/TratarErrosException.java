import java.time.format.DateTimeParseException;
import java.util.*;

public class TratarErrosException extends Exception{

    private static String gerarMensagem(Exception e) {
        if (e instanceof NumberFormatException) {
            return "digitou um texto onde um número era esperado";
        } else if (e instanceof DateTimeParseException) {
            return "digitou uma data em um formato inválido";
        } else if (e instanceof InputMismatchException) {
            return "digitou um tipo de dado completamente diferente do esperado";
        } else if (e instanceof NullPointerException) {
            return "digitou em branco!";
        } else if (e instanceof java.io.IOException) {
            return "digitou uma data sem tarefas";
        } else {
            return "encontrou um erro inesperado";
        }
    }
    public static void imprimirErroFormatado(Exception e) {
        String mensagem = gerarMensagem(e);
        System.out.println("Você " + mensagem + "!");
        System.err.println("DEBUG: " + e);

    }
}

