import java.io.IOException;

/**
 * Just a program entry point.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer();
        lex.startLexicalAnalysis();
        System.out.println(" Parsing completed ");
    }
}
