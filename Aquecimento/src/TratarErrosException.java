import java.time.format.DateTimeParseException;
import java.util.*;

public class TratarErrosException extends Exception{

    private static String gerarMensagem(Exception e) {
        return switch (e) {
            case NumberFormatException numberFormatException -> "digitou um texto onde um número era esperado";
            case DateTimeParseException dateTimeParseException -> "digitou uma data em um formato inválido";
            case InputMismatchException inputMismatchException -> "digitou um tipo de dado completamente diferente do esperado";
            case NullPointerException nullPointerException -> "digitou em branco!";
            case java.io.IOException ioException -> "digitou uma data sem tarefas";
            case ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException -> "digitou espaço em branco na data!";
            case null, default -> "encontrou um erro inesperado";
        };
    }
    public static void imprimirErroFormatado(Exception e) {
        String mensagem = gerarMensagem(e);
        System.out.println("Você " + mensagem + "!");
    }
}

