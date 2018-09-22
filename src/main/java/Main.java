import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer();
        lex.startLexicalAnalysis();
        System.out.println("Parsing completed");
    }
}
