import java.io.IOException;
import java.util.ArrayList;

/**
 * Just a program entry point.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer();
        Reader reader = new Reader("in.txt");
        Writer writer = new Writer("out.txt");
        String line;
        while ((line = reader.nextLine()) != null) {
            ArrayList<Token> tokens = lex.startLexicalAnalysis(line);
            if (!tokens.isEmpty()) {
                for (Token token : tokens) {
                    writer.write(token);
                }
            }
            writer.writeEmptyLine();
            }
        System.out.println(" Parsing completed ");
    }
}
